package com.folbstudio.mobiletechnicalchallange.models

import android.graphics.Bitmap

class Ads(val items: List<Ad>) {
    val size get() = items.size

    fun getImageUrls(): List<String> {
        return items.map {
            it.image.url
        }
    }

    fun setImageBitmap(bitmap: Bitmap, index: Int) {
        if (index < size) {
            items[index].imageBitmap = bitmap
        }
    }
}

class Ad (
    val description: String,
    val id: String,
    val location: String,
    val image: Image,
    var imageBitmap: Bitmap,
    val price: Price?) {

    var favorite = false

    fun toggleFavorite() {
        favorite = !favorite
    }

    fun getPrice(): String {
        return if (price == null) {
            "Gis bort"
        } else {
            "${price.value} kr"
        }
    }
}

class Image(val url: String)

class Price(val value: Int)