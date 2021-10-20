package com.yasir.sculpture.arena.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
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
        holder.bind(differ.currentList[position], position)
    }

    override fun getItemCount() = differ.currentList.size

    /*fun setPhotosList(photosList: List<PhotoResponseModelItem>) {
        photoResponseItems.clear()
        photoResponseItems.addAll(photosList)
        notifyDataSetChanged()
    }*/

    inner class PhotoListViewHolder(val itemBinding: PhotosItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(photos: PhotoResponseModelItem, position: Int) {
            itemBinding.apply {
               /* ivPhotos.load(photoResponseModel.urls.thumb) {
                    placeholder(R.color.white)
                    crossfade(true)
                }*/
                photoResponseModel = photos
                cvPhotos.setOnClickListener {
                    onPhotoSelected(photos, position)
                }
            }
        }
    }
    private val differCallBack  = object : DiffUtil.ItemCallback<PhotoResponseModelItem>()
    {

        override fun areItemsTheSame(oldItem: PhotoResponseModelItem, newItem: PhotoResponseModelItem): Boolean {
            return  oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoResponseModelItem, newItem: PhotoResponseModelItem): Boolean {
            return  oldItem==newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
}
