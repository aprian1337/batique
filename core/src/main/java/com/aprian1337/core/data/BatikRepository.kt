package com.aprian1337.core.data

import com.aprian1337.core.domain.model.Batik
import com.aprian1337.core.domain.repository.IBatikRepository
import com.aprian1337.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BatikRepository @Inject constructor(
    private val remoteData: com.aprian1337.core.data.source.remote.RemoteDataSource,
    private val localData: com.aprian1337.core.data.source.local.LocalDataSource
) : IBatikRepository {
    override fun getAllBatik(): Flow<com.aprian1337.core.data.Status<List<Batik>>> =
        object : com.aprian1337.core.data.NetworkBoundResource<List<Batik>, com.aprian1337.core.data.source.remote.response.BatikResponse>() {
            override fun loadDB(): Flow<List<Batik>> {
                return localData.getAllBatik().map {
                    DataMapper.batikEntToDom(it)
                }
            }

            override fun shouldFetch(data: List<Batik>?): Boolean {
                return data == null || data.isEmpty()
            }
            override suspend fun saveCallResult(data: com.aprian1337.core.data.source.remote.response.BatikResponse) {
                val batik = DataMapper.batikResToEnt(data)
                localData.insertAllBatik(batik)
            }

            override suspend fun apiCall(): Flow<com.aprian1337.core.data.source.remote.network.ApiResponse<com.aprian1337.core.data.source.remote.response.BatikResponse>> = remoteData.getAllBatik()

        }.asFlow()

    override fun getFav(): Flow<List<Batik>> {
        return localData.getFavBatik().map {
            DataMapper.batikEntToDom(it)
        }
    }

    override fun setFav(batik: Batik, isFav: Boolean) {
        val data = DataMapper.batikDomToEnt(batik)
        CoroutineScope(Dispatchers.IO).launch {
            localData.setFavBatik(data, isFav)
        }
    }
}