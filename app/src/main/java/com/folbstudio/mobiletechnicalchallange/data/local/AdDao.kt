package com.folbstudio.mobiletechnicalchallange.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.folbstudio.mobiletechnicalchallange.models.AdEntity

@Dao
interface AdDao {
    @Query("select * from likedAds")
    fun getLikedAds(): List<AdEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLikedAds(likedAds: List<AdEntity>)
}

