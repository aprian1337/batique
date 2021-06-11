package com.aprian1337.core.data

import com.aprian1337.core.data.source.local.LocalDataSource
import com.aprian1337.core.data.source.remote.RemoteDataSource
import com.aprian1337.core.data.source.remote.network.ApiStatus
import com.aprian1337.core.data.source.remote.response.BatikResponse
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
    private val remoteData: RemoteDataSource,
    private val localData: LocalDataSource
) : IBatikRepository {
    override fun getAllBatik(): Flow<Status<List<Batik>>> =
        object : NetworkBoundResource<List<Batik>, BatikResponse>() {
            override fun loadDB(): Flow<List<Batik>> {
                return localData.getAllBatik().map {
                    DataMapper.batikListEntToDom(it)
                }
            }

            override fun shouldFetch(data: List<Batik>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun saveCallResult(data: BatikResponse) {
                val batik = DataMapper.batikResToListEnt(data)
                localData.insertAllBatik(batik)
            }

            override suspend fun apiCall(): Flow<ApiStatus<BatikResponse>> =
                remoteData.getAllBatik()

        }.asFlow()

    override fun getDetailBatik(namaBatik: String): Flow<Status<Batik>> =
        object : NetworkBoundResource<Batik, BatikResponse>() {
            override fun loadDB(): Flow<Batik> = localData.getDetailBatik(namaBatik).map {
                DataMapper.batikEntToDom(it)
            }

            override fun shouldFetch(data: Batik?): Boolean {
                return data?.idBatik == null
            }

            override suspend fun apiCall(): Flow<ApiStatus<BatikResponse>> =
                remoteData.getDetailBatik(namaBatik)

            override suspend fun saveCallResult(data: BatikResponse){
                val detail = DataMapper.batikResToEnt(data)
                localData.insertDetailBatik(detail)
            }

        }.asFlow()

    override fun getFav(): Flow<List<Batik>> {
        return localData.getFavBatik().map {
            DataMapper.batikListEntToDom(it)
        }
    }

    override fun setFav(batik: Batik, isFav: Boolean) {
        val data = DataMapper.batikDomToEnt(batik)
        CoroutineScope(Dispatchers.IO).launch {
            localData.setFavBatik(data, isFav)
        }
    }
}