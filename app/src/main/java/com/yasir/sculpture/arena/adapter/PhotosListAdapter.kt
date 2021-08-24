package com.yasir.sculpture.arena.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yasir.sculpture.arena.R
import com.yasir.sculpture.arena.api.models.PhotoResponseModelItem
import com.yasir.sculpture.arena.databinding.PhotosItemLayoutBinding

class PhotosListAdapter(val onPhotoSelected: (photo: PhotoResponseModelItem, position: Int) -> Unit) : RecyclerView.Adapter<PhotosListAdapter.PhotoListViewHolder>() {

    private val photoResponseItems: ArrayList<PhotoResponseModelItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        val binding = PhotosItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        holder.bind(photoResponseItems[position], position)
    }

    override fun getItemCount() = photoResponseItems.size

    fun setPhotosList(photosList: List<PhotoResponseModelItem>) {
        photoResponseItems.clear()
        photoResponseItems.addAll(photosList)
        notifyDataSetChanged()
    }

    inner class PhotoListViewHolder(val itemBinding: PhotosItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(photoResponseModel: PhotoResponseModelItem, position: Int) {
            itemBinding.apply {
                ivPhotos.load(photoResponseModel.urls.thumb) {
                    placeholder(R.color.white)
                    crossfade(true)
                }

                cvPhotos.setOnClickListener {
                    onPhotoSelected(photoResponseModel, position)
                }
            }
        }
    }
}
