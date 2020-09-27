package com.folbstudio.mobiletechnicalchallange.utils

interface ResponseCallback<T> {

    fun onSuccess(response: T)

    fun onFailure(errorMessage: String)
}