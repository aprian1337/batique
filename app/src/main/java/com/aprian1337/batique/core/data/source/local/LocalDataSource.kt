package com.aprian1337.batique.core.data.source.local

import com.aprian1337.batique.core.data.source.local.entity.BatikEntity
import com.aprian1337.batique.core.data.source.local.room.BatikDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dao: BatikDao
){
    fun getAllBatik() : Flow<List<BatikEntity>> = dao.getAllBatik()

    fun getFavBatik() : Flow<List<BatikEntity>> = dao.getFavBatik()

    suspend fun insertAllBatik(batik: List<BatikEntity>) = dao.insertAllBatik(batik)

    fun setFavBatik(batik: BatikEntity, state:Boolean){
        batik.isFavorite = state
        dao.updateFavBatik(batik)
    }
}