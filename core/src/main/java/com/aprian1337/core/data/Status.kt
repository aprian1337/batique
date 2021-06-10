package com.aprian1337.core.data

sealed class Status<T>(val data: T? = null, val message: String? = null) {
    class SUCCESS<T>(data: T) : Status<T>(data)
    class LOADING<T>(data: T? = null) : Status<T>(data)
    class ERROR<T>(message: String, data: T? = null) : Status<T>(data, message)
}