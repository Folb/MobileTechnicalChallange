package com.folbstudio.mobiletechnicalchallange.data.local

import com.folbstudio.mobiletechnicalchallange.MainApplication
import com.folbstudio.mobiletechnicalchallange.models.AdEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

object AdsLocalDataSource {
    val adDao = AppDatabase.getInstance(MainApplication.instance).adDao()

    suspend fun insertLikedAds(likedAds: List<AdEntity>) {
        withContext(IO) {
            adDao.updateLikedAds(likedAds)
        }
    }

    fun getAllLikedAds(): List<AdEntity> {
        return adDao.getLikedAds()
    }


}