package com.android.framekit.extensions

inline fun <T, reified R> T.transform(clazz: Class<R>): R? {
    return if (this is R) {
        this
    } else null
}