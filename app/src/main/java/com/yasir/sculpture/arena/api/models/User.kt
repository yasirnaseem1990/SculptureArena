package com.yasir.sculpture.arena.api.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @Expose val accepted_tos: Boolean,
    @Expose val bio: String,
    @Expose val first_name: String,
    @Expose val for_hire: Boolean,
    @Expose val id: String,
    @Expose val instagram_username: String,
    @Expose val last_name: String,
    @Expose val location: String,
    @Expose val name: String,
    @Expose val portfolio_url: String,
    @Expose val total_collections: Int,
    @Expose val total_likes: Int,
    @Expose val total_photos: Int,
    @Expose val twitter_username: String,
    @Expose val updated_at: String,
    @Expose val username: String
): Parcelable