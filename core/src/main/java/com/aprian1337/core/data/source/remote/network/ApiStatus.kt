package com.aprian1337.core.data.source.remote.network

sealed class ApiStatus<out R> {
    data class Success<out T>(val data: T) : com.aprian1337.core.data.source.remote.network.ApiStatus<T>()
    data class Error(val errorMessage: String) : com.aprian1337.core.data.source.remote.network.ApiStatus<Nothing>()
    object Empty : com.aprian1337.core.data.source.remote.network.ApiStatus<Nothing>()
}