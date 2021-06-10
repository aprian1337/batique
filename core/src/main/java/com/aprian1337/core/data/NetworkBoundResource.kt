package com.aprian1337.core.data

import com.aprian1337.core.data.source.remote.network.ApiStatus
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResType, ReqType> {
    private var data: Flow<Status<ResType>> = flow{
        emit(Status.LOADING())
        val db = loadDB().first()
        if(shouldFetch(db)){
            emit(Status.LOADING())
            when(val responses = apiCall().first()){
                is ApiStatus.Success -> {
                    saveCallResult(responses.data)
                    emitAll(loadDB().map {
                        Status.SUCCESS(it)
                    })
                }
                is ApiStatus.Empty -> {
                    emitAll(
                        loadDB().map {
                            Status.SUCCESS(it)
                        }
                    )
                }
                is ApiStatus.Error -> {
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

    protected abstract suspend fun apiCall(): Flow<ApiStatus<ReqType>>

    protected abstract suspend fun saveCallResult(data: ReqType)

    fun asFlow(): Flow<Status<ResType>> = data
}