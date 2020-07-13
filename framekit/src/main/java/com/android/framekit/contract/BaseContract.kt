package com.android.framekit.contract

interface BaseContract {
    interface BasePresenter<in T> {
        fun attachView(view: T)
        fun detachView()
    }

    interface BaseView {
        fun showError(e: Throwable)
        fun complete()
    }
}
