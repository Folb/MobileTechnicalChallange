package com.folbstudio.mobiletechnicalchallange.api

import com.folbstudio.mobiletechnicalchallange.models.Ads
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.io.File

interface Endpoints {
    @GET("/baldermork/6a1bcc8f429dcdb8f9196e917e5138bd/raw/a542886ef057c91d822004ed15881f0c25221c18/discover.json")
    fun getAdData(): Call<Ads>

    @GET("/dynamic/480x360c/{imageUrl}")
    fun getAdImage(@Path("imageUrl", encoded = true) imageUrl: String): Call<ResponseBody>
}