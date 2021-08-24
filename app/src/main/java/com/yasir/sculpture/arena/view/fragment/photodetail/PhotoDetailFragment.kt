package com.yasir.sculpture.arena.view.fragment.photodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.yasir.sculpture.arena.api.models.PhotoResponseModelItem
import com.yasir.sculpture.arena.base.BaseFragment
import com.yasir.sculpture.arena.databinding.FragmentPhotoDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhotoDetailFragment : BaseFragment<FragmentPhotoDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPhotoDetailBinding
        get() = FragmentPhotoDetailBinding::inflate

    private val viewModel: PhotoDetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photo = arguments?.getParcelable<PhotoResponseModelItem>("photo")

        initObservers()

        viewModel.getPhotoItem(photo)
    }

    companion object {}

    private fun initObservers() {
        viewModel.photoResponseModelItem.observe(viewLifecycleOwner) { photo ->
            _binding?.ivPhotoView?.load(photo?.urls?.full)
        }
    }
}