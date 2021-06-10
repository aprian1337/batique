package com.aprian1337.batique.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aprian1337.core.domain.usecase.BatikUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(useCase : BatikUseCase) : ViewModel() {
    val batik = useCase.getAllBatik().asLiveData()
}