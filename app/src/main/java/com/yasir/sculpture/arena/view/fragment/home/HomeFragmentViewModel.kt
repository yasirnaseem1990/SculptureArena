package com.yasir.sculpture.arena.view.fragment.home

import androidx.lifecycle.*
import com.yasir.sculpture.arena.api.models.PhotoResponseModelItem
import com.yasir.sculpture.arena.api.onError
import com.yasir.sculpture.arena.api.onSuccess
import com.yasir.sculpture.arena.repository.PhotosRepository
import kotlinx.coroutines.launch
import timber.log.Timber


class HomeFragmentViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    private var _homeFragmentUiState = MutableLiveData<HomeFragmentUIState>()
    var fragmentUIStateLiveData: LiveData<HomeFragmentUIState> = _homeFragmentUiState

    private var _photosResponseModelList = MutableLiveData<List<PhotoResponseModelItem>>()
    var photosResponseModelListData: LiveData<List<PhotoResponseModelItem>> = _photosResponseModelList

    private var pageNumber = 1
    private var searchQuery: String = ""

    init {
        getLatestPhotos(pageNumber)
    }

    fun loadMorePhotos() {
        pageNumber++
        if (searchQuery == "") getLatestPhotos(pageNumber) else searchPhotos(searchQuery, pageNumber)
    }

    fun retry() {
        if (searchQuery == "")
            getLatestPhotos(pageNumber)
        else
            searchPhotos(searchQuery, pageNumber)
    }

    fun searchPhotos(query: String) {
        searchQuery = query
        pageNumber = 1
        searchPhotos(query, pageNumber)
    }

    fun getLatestPhotos(page: Int) {
        _homeFragmentUiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            photosRepository.getLatestPhotos(page)
                .onSuccess {
                    if (page == 1) {
                        _homeFragmentUiState.postValue(ContentState)
                        _photosResponseModelList.postValue(it)
                    } else {
                        _homeFragmentUiState.postValue(ContentNextPageState)
                        val currentList = arrayListOf<PhotoResponseModelItem>()
                        _photosResponseModelList.value?.let { currentList.addAll(it) }
                        currentList.addAll(it)
                        _photosResponseModelList.postValue(currentList)
                    }
                }
                .onError { error ->
                    Timber.e(error.message)
                    if (page == 1) {
                        _homeFragmentUiState.postValue(ErrorState(error.message))
                    } else {
                        _homeFragmentUiState.postValue(ErrorNextPageState(error.message))
                    }
                }
        }
    }

    private fun searchPhotos(query: String, page: Int) {
        _homeFragmentUiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            photosRepository.getSearchPhotos(query, page)
                .onSuccess {
                    if (page == 1) {
                        _homeFragmentUiState.postValue(ContentState)
                        _photosResponseModelList.postValue(it.results)
                    } else {
                        _homeFragmentUiState.postValue(ContentNextPageState)
                        val currentList = arrayListOf<PhotoResponseModelItem>()
                        _photosResponseModelList.value?.let { currentList.addAll(it) }
                        currentList.addAll(it.results)
                        _photosResponseModelList.postValue(currentList)
                    }
                }
                .onError { error ->
                    Timber.e(error.message)
                    if (page == 1) {
                        _homeFragmentUiState.postValue(ErrorState(error.message))
                    } else {
                        _homeFragmentUiState.postValue(ErrorNextPageState(error.message))
                    }
                }
        }
    }
}
