package com.folbstudio.mobiletechnicalchallange.models

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.folbstudio.mobiletechnicalchallange.utils.likedIcon
import com.folbstudio.mobiletechnicalchallange.utils.notLikedIcon

class Ads(val items: List<Ad>) {
    val size get() = items.size

    fun getImageUrls(): List<String> {
        return items.map {
            it.image?.url ?: ""
        }
    }

    fun setImageBitmap(bitmap: Bitmap, index: Int) {
        if (index < size) {
            items[index].imageBitmap = bitmap
        }
    }

    fun getFavoritedAds(): List<Ad> {
        return items.filter {
            it.favorite
        }
    }
}

class Ad (
    val description: String,
    val id: String,
    val location: String,
    val image: Image?,
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

    fun getIcon(): Drawable? {
        val icon = if (favorite) {
            likedIcon
        } else {
            notLikedIcon
        }

        return icon
    }
}

class Image(val url: String)

class Price(val value: Int)