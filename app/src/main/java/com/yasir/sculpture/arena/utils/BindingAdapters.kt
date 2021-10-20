package com.yasir.sculpture.arena.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load


@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        view.load(url)
    }
}