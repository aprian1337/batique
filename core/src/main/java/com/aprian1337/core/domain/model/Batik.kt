package com.aprian1337.core.domain.model

data class Batik(
    val idBatik: Int,
    val namaBatik: String,
    val daerahBatik: String,
    val maknaBatik: String,
    val hargaRendah: Int,
    val hargaTinggi: Int,
    val imgBatik: String,
    val isFavorite: Boolean?
)