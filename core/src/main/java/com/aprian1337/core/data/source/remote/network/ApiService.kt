package com.aprian1337.core.data.source.remote.network

import com.aprian1337.core.data.source.remote.response.BatikResponse
import com.aprian1337.core.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(Constants.ENDPOINT_BATIK_ALL)
    suspend fun getBatik(): BatikResponse

    @GET
    suspend fun getDetailBatik(
        @Path("namaBatik") namaBatik: String
    ): BatikResponse
}