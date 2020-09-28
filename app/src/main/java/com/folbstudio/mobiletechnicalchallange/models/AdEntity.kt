package com.folbstudio.mobiletechnicalchallange.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "likedAds")
data class AdEntity(
    @PrimaryKey
    val id: String,
    val description: String,
    val price: Int?,
    val imageBitmap: String,
    val location: String
)