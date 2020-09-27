package com.folbstudio.mobiletechnicalchallange.layout.adview

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.folbstudio.mobiletechnicalchallange.data.AdRepository
import com.folbstudio.mobiletechnicalchallange.models.Ads
import com.folbstudio.mobiletechnicalchallange.utils.ObservableViewModel
import com.folbstudio.mobiletechnicalchallange.utils.ResponseCallback

class AdCollectionViewModel : ObservableViewModel() {

    val tag = this::class.java.canonicalName

    private val repository = AdRepository

    private val _ads: MutableLiveData<Ads> = MutableLiveData()
    val ads: LiveData<Ads> get() = _ads

    init {
        repository.getAdData(object : ResponseCallback<Ads> {
            override fun onSuccess(response: Ads) {
                Log.e("ViewModel", "Ads successfully fetched")
                setAds(response)

                val imageUrls = _ads.value?.getImageUrls()
                imageUrls?.let {
                    repository.getAdImages(it, object : ResponseCallback<Pair<Bitmap, Int>> {
                        override fun onSuccess(response: Pair<Bitmap, Int>) {
                            _ads.value?.setImageBitmap(response.first, response.second)
                            notifyChange()
                        }

                        override fun onFailure(errorMessage: String) {
                            Log.e("ViewModel", errorMessage)
                        }
                    })
                }
            }

            override fun onFailure(errorMessage: String) {
                Log.e(tag, errorMessage)
            }
        })
    }

    fun setAds(ads: Ads) {
        _ads.value = ads
        notifyChange()
    }
}