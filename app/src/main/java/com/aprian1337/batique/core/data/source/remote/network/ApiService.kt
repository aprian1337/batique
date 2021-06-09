package com.aprian1337.batique.core.data.source.remote.network

import com.aprian1337.batique.core.data.source.remote.response.BatikResponse
import com.aprian1337.batique.core.utils.Constants
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.ENDPOINT_BATIK_ALL)
    suspend fun getBatik(): BatikResponse
}