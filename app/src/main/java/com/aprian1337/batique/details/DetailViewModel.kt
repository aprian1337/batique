package com.aprian1337.batique.details

import androidx.lifecycle.ViewModel
import com.aprian1337.batique.core.data.BatikRepository
import com.aprian1337.batique.core.domain.model.Batik
import com.aprian1337.batique.core.domain.usecase.BatikUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: BatikUseCase) : ViewModel() {
    fun setFav(batik: Batik, isFav: Boolean) = useCase.setFavBatik(batik, isFav)
}