package com.aprian1337.core.data.source.remote.network

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : com.aprian1337.core.data.source.remote.network.ApiResponse<T>()
    data class Error(val errorMessage: String) : com.aprian1337.core.data.source.remote.network.ApiResponse<Nothing>()
    object Empty : com.aprian1337.core.data.source.remote.network.ApiResponse<Nothing>()
}