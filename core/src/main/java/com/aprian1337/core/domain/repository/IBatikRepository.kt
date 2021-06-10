package com.aprian1337.core.domain.repository

import com.aprian1337.core.data.Status
import com.aprian1337.core.domain.model.Batik
import kotlinx.coroutines.flow.Flow

interface IBatikRepository {
    fun getAllBatik(): Flow<Status<List<Batik>>>

    fun getDetailBatik(namaBatik: String): Flow<Status<Batik>>

    fun getFav(): Flow<List<Batik>>

    fun setFav(batik: Batik, isFav: Boolean)
}