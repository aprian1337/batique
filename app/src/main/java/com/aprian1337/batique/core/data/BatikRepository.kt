package com.aprian1337.batique.core.data

import com.aprian1337.batique.core.data.source.local.LocalDataSource
import com.aprian1337.batique.core.data.source.remote.RemoteDataSource
import com.aprian1337.batique.core.data.source.remote.network.ApiResponse
import com.aprian1337.batique.core.data.source.remote.response.BatikResponse
import com.aprian1337.batique.core.data.source.remote.response.HasilItem
import com.aprian1337.batique.core.domain.model.Batik
import com.aprian1337.batique.core.domain.repository.IBatikRepository
import com.aprian1337.batique.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BatikRepository @Inject constructor(
    private val remoteData: RemoteDataSource,
    private val localData: LocalDataSource
) : IBatikRepository {
    override fun getAllBatik(): Flow<Status<List<Batik>>> =
        object : NetworkBoundResource<List<Batik>, BatikResponse>() {
            override fun loadDB(): Flow<List<Batik>> {
                return localData.getAllBatik().map {
                    DataMapper.batikEntToDom(it)
                }
            }

            override fun shouldFetch(data: List<Batik>?): Boolean {
                return data == null || data.isEmpty()
            }
            override suspend fun saveCallResult(data: BatikResponse) {
                val batik = DataMapper.batikResToEnt(data)
                localData.insertAllBatik(batik)
            }

            override suspend fun apiCall(): Flow<ApiResponse<BatikResponse>> = remoteData.getAllBatik()

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