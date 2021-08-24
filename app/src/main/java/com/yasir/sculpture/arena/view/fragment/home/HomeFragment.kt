package com.yasir.sculpture.arena.view.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.NestedScrollView
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.yasir.sculpture.arena.R
import com.yasir.sculpture.arena.adapter.PhotosListAdapter
import com.yasir.sculpture.arena.base.BaseFragment
import com.yasir.sculpture.arena.databinding.FragmentHomeBinding
import com.yasir.sculpture.arena.utils.*


class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: HomeFragmentViewModel by viewModel()

    private lateinit var photosListAdapter: PhotosListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        photosListAdapter = PhotosListAdapter() { photo, position ->

        }

        _binding?.recyclerPopularPhotos?.apply {
            photosListAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = photosListAdapter
        }

        _binding?.nestedScrollView?.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                viewModel.loadMorePhotos()
            }
        }

        _binding?.tilSearchPhotos?.setEndIconOnClickListener {
            _binding?.txtSearchPhotos?.setText("")
            _binding?.tvLabelHighlights?.text = getString(R.string.txt_highlights)
            viewModel.getLatestPhotos(1)
        }

        _binding?.txtSearchPhotos?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                _binding?.txtSearchPhotos?.dismissKeyboard()
                val searchQuery = _binding?.txtSearchPhotos?.text.toString()
                _binding?.txtSearchPhotos?.setText(searchQuery)
                _binding?.tvLabelHighlights?.setText(getString(R.string.txt_search_results_for, searchQuery))
                viewModel.searchPhotos(searchQuery)
                true
            }
            false
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.fragmentUIStateLiveData.observe(viewLifecycleOwner) { homeFragmentUiState ->
            when (homeFragmentUiState) {
                is LoadingState -> {
                    _binding?.recyclerPopularPhotos?.gone()
                    _binding?.progressPhotos?.visible()
                }
                is LoadingNextPageState -> {
                    _binding?.progressPhotos?.gone()
                    showToast(getString(R.string.loading))
                }
                is ContentState -> {
                    _binding?.recyclerPopularPhotos?.visible()
                    _binding?.progressPhotos?.gone()
                }
                is ErrorState -> {
                    _binding?.progressPhotos?.gone()
                    _binding?.nestedScrollView?.showSnack(homeFragmentUiState.message, getString(R.string.txt_retry)) {
                        viewModel.retry()
                    }
                }

                is ErrorNextPageState -> {
                    _binding?.nestedScrollView?.showSnack(homeFragmentUiState.message, getString(R.string.txt_retry)) {
                        viewModel.retry()
                    }
                }
                else -> {

                }
            }
        }
        viewModel.photosResponseModelListData.observe(viewLifecycleOwner) { photos ->
            photosListAdapter.setPhotosList(photos)
        }
    }

    companion object {}
}