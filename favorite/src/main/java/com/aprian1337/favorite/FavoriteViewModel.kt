package com.aprian1337.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aprian1337.core.domain.usecase.BatikUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class FavoriteViewModel constructor(useCase : BatikUseCase) : ViewModel() {
    val batik = useCase.getFavBatik().asLiveData()
}