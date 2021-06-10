package com.aprian1337.core.data.source.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: com.aprian1337.core.data.source.remote.network.ApiService
) {
    suspend fun getAllBatik(): Flow<com.aprian1337.core.data.source.remote.network.ApiResponse<com.aprian1337.core.data.source.remote.response.BatikResponse>> {
        return flow{
            try {
                val responses = apiService.getBatik()
                val data = responses.hasil
                if(!data.isEmpty()){
                    emit(com.aprian1337.core.data.source.remote.network.ApiResponse.Success(responses))
                }else{
                    emit(com.aprian1337.core.data.source.remote.network.ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(com.aprian1337.core.data.source.remote.network.ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}