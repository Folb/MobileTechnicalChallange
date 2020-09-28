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

    var showOnlyFavorites = false

    private val _toggleButtonText: MutableLiveData<String>
        get() {
            return if (showOnlyFavorites) {
                MutableLiveData("Vis alle")
            } else {
                MutableLiveData("Vis kun favoritter")
            }
        }
    val toggleButtonText: LiveData<String> get() = _toggleButtonText

    init {
        repository.getAdData(object : ResponseCallback<Ads> {
            override fun onSuccess(response: Ads) {
                Log.e("ViewModel", "Ads successfully fetched")
                _ads.value = response
                notifyChange()

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

    fun toggleVisibility() {
        showOnlyFavorites = !showOnlyFavorites
        notifyChange()
    }

    fun cheat() {
        notifyChange()
    }
}

