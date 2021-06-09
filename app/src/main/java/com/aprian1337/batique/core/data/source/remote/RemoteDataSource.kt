package com.aprian1337.batique.core.data.source.remote

import com.aprian1337.batique.core.data.source.remote.network.ApiResponse
import com.aprian1337.batique.core.data.source.remote.network.ApiService
import com.aprian1337.batique.core.data.source.remote.response.BatikResponse
import com.aprian1337.batique.core.data.source.remote.response.HasilItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllBatik(): Flow<ApiResponse<BatikResponse>> {
        return flow{
            try {
                val responses = apiService.getBatik()
                val data = responses.hasil
                if(!data.isEmpty()){
                    emit(ApiResponse.Success(responses))
                }else{
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}