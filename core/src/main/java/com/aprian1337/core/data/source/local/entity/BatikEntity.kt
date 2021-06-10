package com.aprian1337.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aprian1337.core.utils.Constants

@Entity(tableName = Constants.TABLE_NAME_BATIK)
data class BatikEntity(
    @PrimaryKey
    val batikId: Int,
    val namaBatik: String,
    val daerahBatik: String,
    val maknaBatik: String,
    val hargaRendah: Int,
    val hargaTinggi: Int,
    val imgBatik: String,
    var isFavorite: Boolean?
)