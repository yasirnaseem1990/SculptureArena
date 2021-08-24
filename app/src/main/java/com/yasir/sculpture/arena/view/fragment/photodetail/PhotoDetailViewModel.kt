package com.yasir.sculpture.arena.view.fragment.photodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yasir.sculpture.arena.api.models.PhotoResponseModelItem

class PhotoDetailViewModel : ViewModel() {


    private var _photoResponseModel = MutableLiveData<PhotoResponseModelItem?>()
    var photoResponseModelItem: LiveData<PhotoResponseModelItem?> = _photoResponseModel

    fun getPhotoItem(photo: PhotoResponseModelItem?) {
        _photoResponseModel.value = photo
    }
}