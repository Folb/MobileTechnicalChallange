package com.folbstudio.mobiletechnicalchallange.data.remote

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.folbstudio.mobiletechnicalchallange.api.Endpoints
import com.folbstudio.mobiletechnicalchallange.api.RetrofitFactory
import com.folbstudio.mobiletechnicalchallange.models.Ads
import com.folbstudio.mobiletechnicalchallange.utils.ResponseCallback
import com.folbstudio.mobiletechnicalchallange.utils.imageBaseUrl
import com.folbstudio.mobiletechnicalchallange.utils.adDataUrl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AdsRemoteDataSource {

    private val adImageClient = RetrofitFactory.getClient(imageBaseUrl).create(Endpoints::class.java)
    private val adDataClient = RetrofitFactory.getClientWithGsonConverter(adDataUrl).create(Endpoints::class.java)

    fun fetchAdData(callback: ResponseCallback<Ads>) {
        adDataClient.getAdData().enqueue(object : Callback<Ads> {
            override fun onResponse(call: Call<Ads>?, response: Response<Ads>?) {
                if (response != null && response.isSuccessful) {
                    callback.onSuccess(response.body())
                } else {
                    callback.onFailure("Request failed due to unexpected error :(")
                }
            }

            override fun onFailure(call: Call<Ads>?, t: Throwable?) {
                if (t != null) {
                    callback.onFailure("Request failed with error: $t")
                } else {
                    callback.onFailure("Request failed due to unexpected error :(")
                }
            }
        })
    }

    fun fetchAdImages(urls: List<String>, callback: ResponseCallback<Pair<Bitmap, Int>>) {
        for (i in urls.indices) {
            val url = urls[i]
            adImageClient.getAdImage(url).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response != null && response.isSuccessful) {
                        val bitmap = BitmapFactory.decodeStream(response.body().byteStream())
                        callback.onSuccess(Pair(bitmap, i))
                    } else {
                        callback.onFailure("Request failed due to unexpected error :(")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    if (t != null) {
                        callback.onFailure("Request failed with error: $t")
                    } else {
                        callback.onFailure("Request failed due to unexpected error :(")
                    }
                }
            })
        }
    }
}
