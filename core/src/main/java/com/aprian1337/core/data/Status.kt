package com.aprian1337.core.data

sealed class Status<T>(val data: T? = null, val message: String? = null) {
    class SUCCESS<T>(data: T) : com.aprian1337.core.data.Status<T>(data)
    class LOADING<T>(data: T? = null) : com.aprian1337.core.data.Status<T>(data)
    class ERROR<T>(message: String, data: T? = null) : com.aprian1337.core.data.Status<T>(data, message)
}