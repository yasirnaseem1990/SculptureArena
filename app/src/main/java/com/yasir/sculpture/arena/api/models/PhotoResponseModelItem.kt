package com.yasir.sculpture.arena.api.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoResponseModelItem(
    @Expose val alt_description: String,
    @Expose val blur_hash: String,
    @Expose val categories: List<String>,
    @Expose val color: String,
    @Expose val created_at: String,
    @Expose val current_user_collections: List<String>,
    @Expose val description: String,
    @Expose val height: Int,
    @Expose val id: String,
    @Expose val liked_by_user: Boolean,
    @Expose val likes: Int,
    @Expose val promoted_at: String,
    @Expose val updated_at: String,
    @Expose val urls: Urls,
    @Expose val user: User,
    @Expose val width: Int
) : Parcelable