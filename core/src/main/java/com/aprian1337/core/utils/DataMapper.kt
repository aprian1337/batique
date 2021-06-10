package com.aprian1337.core.utils

import com.aprian1337.core.data.source.local.entity.BatikEntity
import com.aprian1337.core.data.source.remote.response.BatikResponse
import com.aprian1337.core.domain.model.Batik

object DataMapper {
    fun batikResToEnt(data: BatikResponse): List<BatikEntity> {
        val batikArrayList = ArrayList<BatikEntity>()
        data.hasil.map {
            val batik = BatikEntity(
                it.id,
                it.namaBatik,
                it.daerahBatik,
                it.maknaBatik,
                it.hargaRendah,
                it.hargaTinggi,
                it.linkBatik,
                false,
            )
            batikArrayList.add(batik)
        }
        return batikArrayList
    }

    fun batikEntToDom(data: List<BatikEntity>): List<Batik> =
        data.map {
            Batik(
                it.batikId,
                it.namaBatik,
                it.daerahBatik,
                it.maknaBatik,
                it.hargaRendah,
                it.hargaTinggi,
                it.imgBatik,
                it.isFavorite,
            )
        }

    fun batikDomToEnt(data: Batik) = BatikEntity(
        data.idBatik,
        data.namaBatik,
        data.daerahBatik,
        data.maknaBatik,
        data.hargaRendah,
        data.hargaTinggi,
        data.imgBatik,
        data.isFavorite,
    )
}