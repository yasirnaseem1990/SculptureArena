package com.yasir.sculpture.arena.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchPhotosResponseModel(
    @Expose val total: Int,
    @Expose val total_pages: Int,
    @Expose val results: List<PhotoResponseModelItem>
)
