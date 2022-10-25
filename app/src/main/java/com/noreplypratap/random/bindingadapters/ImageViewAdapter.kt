package com.noreplypratap.random.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("ImageFromURL")
fun ImageView.ImagefromURL(imageURLS: String) {
    Glide.with(this).load(imageURLS).into(this)
}