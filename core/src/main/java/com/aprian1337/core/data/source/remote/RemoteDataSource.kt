package com.aprian1337.core.data.source.remote

import com.aprian1337.core.data.source.remote.network.ApiService
import com.aprian1337.core.data.source.remote.network.ApiStatus
import com.aprian1337.core.data.source.remote.response.BatikResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllBatik(): Flow<ApiStatus<BatikResponse>> {
        return flow{
            try {
                val responses = apiService.getBatik()
                val data = responses.hasil
                if(data.isNotEmpty()){
                    emit(ApiStatus.Success(responses))
                }else{
                    emit(ApiStatus.Empty)
                }
            } catch (e: Exception){
                emit(ApiStatus.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailBatik(namaBatik: String): Flow<ApiStatus<BatikResponse>> {
        return flow{
            try {
                val responses = apiService.getDetailBatik(namaBatik)
                val data = responses.hasil
                if(data.isNotEmpty()){
                    emit(ApiStatus.Success(responses))
                }else{
                    emit(ApiStatus.Empty)
                }
            } catch (e: Exception){
                emit(ApiStatus.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}