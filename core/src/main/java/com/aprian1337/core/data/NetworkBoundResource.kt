package com.aprian1337.core.data

import com.aprian1337.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResType, ReqType> {
    private var data: Flow<Status<ResType>> = flow{
        emit(Status.LOADING())
        val db = loadDB().first()
        if(shouldFetch(db)){
            emit(Status.LOADING())
            when(val responses = apiCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(responses.data)
                    emitAll(loadDB().map {
                        Status.SUCCESS(it)
                    })
                }
                is ApiResponse.Empty -> {
                    emitAll(
                        loadDB().map {
                            Status.SUCCESS(it)
                        }
                    )
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Status.ERROR<ResType>(responses.errorMessage))
                }
            }
        }else{
            emitAll(loadDB().map {
                Status.SUCCESS(it)
            })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadDB(): Flow<ResType>

    protected abstract fun shouldFetch(data: ResType?): Boolean

    protected abstract suspend fun apiCall(): Flow<ApiResponse<ReqType>>

    protected abstract suspend fun saveCallResult(data: ReqType)

    fun asFlow(): Flow<Status<ResType>> = data
}