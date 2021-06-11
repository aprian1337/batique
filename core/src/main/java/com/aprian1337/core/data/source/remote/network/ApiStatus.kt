package com.aprian1337.core.data.source.remote.network

sealed class ApiStatus<out R> {
    data class Success<out T>(val data: T) : ApiStatus<T>()
    data class Error(val errorMessage: String) : ApiStatus<Nothing>()
    object Empty : ApiStatus<Nothing>()
}