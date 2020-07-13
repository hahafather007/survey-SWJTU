package com.android.framekit.net.http.okhttp

interface OnHttpListener {

    fun onFailure(error: HttpException)

    fun onSuccess(result: RequestResult)
}