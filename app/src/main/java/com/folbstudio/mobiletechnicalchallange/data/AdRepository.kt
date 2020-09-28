package com.folbstudio.mobiletechnicalchallange.data

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.folbstudio.mobiletechnicalchallange.data.local.AdsLocalDataSource
import com.folbstudio.mobiletechnicalchallange.data.remote.AdsRemoteDataSource
import com.folbstudio.mobiletechnicalchallange.models.AdEntity
import com.folbstudio.mobiletechnicalchallange.models.Ads
import com.folbstudio.mobiletechnicalchallange.utils.ResponseCallback
import com.folbstudio.mobiletechnicalchallange.utils.toAds

object AdRepository {
    private val tag = this::class.simpleName

    private val remoteDataSource = AdsRemoteDataSource
    private val localDataSource = AdsLocalDataSource

    private var ads: Ads? = null

    fun getAdData(callBack: ResponseCallback<Ads>) {
        remoteDataSource.fetchAdData(callBack)
    }

    fun getAdImages(imageUrls: List<String>, callback: ResponseCallback<Pair<Bitmap, Int>>) {
        remoteDataSource.fetchAdImages(imageUrls, callback)
    }


    // Stopped by to much work on the main thread :(
    fun unsuccessfullAttemptToRetrieveDatabaseEntries(): MutableLiveData<Ads> {
        val databaseAds = localDataSource.getAllLikedAds()

        remoteDataSource.fetchAdData(object : ResponseCallback<Ads> {
            override fun onSuccess(response: Ads) {
                val imageUrls = ads?.getImageUrls()
                imageUrls?.let {
                    getAdImages(it, object : ResponseCallback<Pair<Bitmap, Int>> {
                        override fun onSuccess(response: Pair<Bitmap, Int>) {
                            ads?.setImageBitmap(response.first, response.second)
                        }

                        override fun onFailure(errorMessage: String) {
                            Log.e(tag, errorMessage)
                        }
                    })
                }
            }

            override fun onFailure(errorMessage: String) {
                Log.e(tag, errorMessage)
            }
        })

        if (databaseAds.isNotEmpty()) {
            syncDataSourceData(databaseAds)
        } else if (ads == null) {
            return MutableLiveData(databaseAds.toAds())
        }

        return MutableLiveData(ads)
    }

    private fun syncDataSourceData(databaseAds: List<AdEntity>) {
        val adMap: List<String> = databaseAds.map { it.id }

        ads?.items?.map {
            if (adMap.contains(it.id)) {
                it.toggleFavorite()
            }
        }
    }
}
