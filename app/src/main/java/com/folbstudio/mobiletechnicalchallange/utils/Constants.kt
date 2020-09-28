package com.folbstudio.mobiletechnicalchallange.utils

import android.graphics.drawable.Drawable
import com.folbstudio.mobiletechnicalchallange.MainApplication
import com.folbstudio.mobiletechnicalchallange.R

const val adDataUrl: String = "https://gist.githubusercontent.com/"
const val imageBaseUrl: String = "https://images.finncdn.no/"

val likedIcon: Drawable? =  MainApplication.instance.getDrawable(R.drawable.ic_star)
val notLikedIcon: Drawable? =  MainApplication.instance.getDrawable(R.drawable.ic_star_border)

const val databaseName: String = "adDatabase"
