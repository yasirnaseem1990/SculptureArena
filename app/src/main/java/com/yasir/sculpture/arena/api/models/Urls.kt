package com.yasir.sculpture.arena.api.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Urls(
    @Expose val full: String,
    @Expose val raw: String,
    @Expose val regular: String,
    @Expose val small: String,
    @Expose val thumb: String
): Parcelable