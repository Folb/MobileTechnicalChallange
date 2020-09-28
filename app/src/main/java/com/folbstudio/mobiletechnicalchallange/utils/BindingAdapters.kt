package com.folbstudio.mobiletechnicalchallange.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, bitmap: Bitmap) {
    imageView.setImageBitmap(bitmap)
}

@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, drawable: Drawable) {
    imageView.setImageDrawable(drawable)
}