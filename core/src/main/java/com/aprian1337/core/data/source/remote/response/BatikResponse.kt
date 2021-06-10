package com.aprian1337.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BatikResponse(

	@field:SerializedName("max_price")
	val maxPrice: Int,

	@field:SerializedName("min_price")
	val minPrice: Int,

	@field:SerializedName("total_halaman")
	val totalHalaman: Int,

	@field:SerializedName("hasil")
	val hasil: List<com.aprian1337.core.data.source.remote.response.HasilItem>,

	@field:SerializedName("total_element")
	val totalElement: Int
)

data class HasilItem(

	@field:SerializedName("daerah_batik")
	val daerahBatik: String,

	@field:SerializedName("harga_rendah")
	val hargaRendah: Int,

	@field:SerializedName("harga_tinggi")
	val hargaTinggi: Int,

	@field:SerializedName("nama_batik")
	val namaBatik: String,

	@field:SerializedName("makna_batik")
	val maknaBatik: String,

	@field:SerializedName("link_batik")
	val linkBatik: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("hitung_view")
	val hitungView: Int
)
