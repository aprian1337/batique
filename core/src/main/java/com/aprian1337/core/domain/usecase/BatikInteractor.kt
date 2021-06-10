package com.aprian1337.core.domain.usecase

import com.aprian1337.core.data.Status
import com.aprian1337.core.domain.model.Batik
import com.aprian1337.core.domain.repository.IBatikRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BatikInteractor @Inject constructor(private val repository: IBatikRepository): BatikUseCase {
    override fun getAllBatik(): Flow<Status<List<Batik>>> = repository.getAllBatik()

    override fun getFavBatik(): Flow<List<Batik>> = repository.getFav()

    override fun setFavBatik(batik: Batik, isFav: Boolean) = repository.setFav(batik, isFav)

}