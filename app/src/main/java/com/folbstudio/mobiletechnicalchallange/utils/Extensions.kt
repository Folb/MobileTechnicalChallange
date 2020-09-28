package com.folbstudio.mobiletechnicalchallange.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.folbstudio.mobiletechnicalchallange.models.Ad
import com.folbstudio.mobiletechnicalchallange.models.AdEntity
import com.folbstudio.mobiletechnicalchallange.models.Ads
import com.folbstudio.mobiletechnicalchallange.models.Price
import java.io.ByteArrayOutputStream


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun List<AdEntity>.toAds(): Ads {
    val items: MutableList<Ad> = mutableListOf()
    for (adEntity in this) {
        val price = if (adEntity.price != null) {
            Price(adEntity.price)
        } else {
            null
        }

        items.add(
            Ad(
                id = adEntity.id,
                description = adEntity.description,
                imageBitmap = adEntity.imageBitmap.toBitmap(),
                location = adEntity.location,
                price = price,
                image = null
            )
        )
    }

    return Ads(items)
}

fun Fragment.navigate(destionation: NavDirections) {
    if (isAdded) {
        with(findNavController()) {
            currentDestination?.getAction(destionation.actionId)?.let {
                navigate(destionation)
            }
        }
    }
}

fun String.toBitmap(): Bitmap {
    val encodeByte: ByteArray = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)

}