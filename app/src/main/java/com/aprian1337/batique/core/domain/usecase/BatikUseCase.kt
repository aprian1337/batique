package com.aprian1337.batique.core.domain.usecase

import com.aprian1337.batique.core.data.Status
import com.aprian1337.batique.core.domain.model.Batik
import kotlinx.coroutines.flow.Flow

interface BatikUseCase {
    fun getAllBatik(): Flow<Status<List<Batik>>>
    fun getFavBatik(): Flow<List<Batik>>
    fun setFavBatik(batik: Batik, isFav: Boolean)
}