package com.folbstudio.mobiletechnicalchallange.data

import android.graphics.Bitmap
import com.folbstudio.mobiletechnicalchallange.models.Ads
import com.folbstudio.mobiletechnicalchallange.utils.ResponseCallback
import java.io.File

object AdRepository {
    private val remoteDataSource = RemoteDataSource

    fun getAdData(callBack: ResponseCallback<Ads>) {
        remoteDataSource.fetchAdData(callBack)
    }

    fun getAdImages(imageUrls: List<String>, callback: ResponseCallback<Pair<Bitmap, Int>>) {
        remoteDataSource.fetchAdImages(imageUrls, callback)
    }
}
