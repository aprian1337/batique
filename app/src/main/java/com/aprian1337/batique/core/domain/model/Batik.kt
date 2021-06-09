package com.aprian1337.batique.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Batik(
    val namaBatik: String,
    val daerahBatik: String,
    val maknaBatik: String,
    val hargaRendah: Int,
    val hargaTinggi: Int,
    val imgBatik: String
) : Parcelable