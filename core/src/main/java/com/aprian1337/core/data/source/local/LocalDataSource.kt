package com.aprian1337.core.data.source.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dao: com.aprian1337.core.data.source.local.room.BatikDao
){
    fun getAllBatik() : Flow<List<com.aprian1337.core.data.source.local.entity.BatikEntity>> = dao.getAllBatik()

    fun getFavBatik() : Flow<List<com.aprian1337.core.data.source.local.entity.BatikEntity>> = dao.getFavBatik()

    suspend fun insertAllBatik(batik: List<com.aprian1337.core.data.source.local.entity.BatikEntity>) = dao.insertAllBatik(batik)

    fun setFavBatik(batik: com.aprian1337.core.data.source.local.entity.BatikEntity, state:Boolean){
        batik.isFavorite = state
        dao.updateFavBatik(batik)
    }
}