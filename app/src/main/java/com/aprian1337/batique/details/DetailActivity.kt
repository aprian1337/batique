package com.aprian1337.batique.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.aprian1337.batique.R
import com.aprian1337.batique.core.domain.model.Batik
import com.aprian1337.batique.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.includeToolbar.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                finish()
            }
        }
        val batik = intent.getParcelableExtra<Batik>(EXTRA_DETAIL) as Batik
        binding.apply {
            tvNamaBatik.text = batik.namaBatik
            tvDeskripsi.text = batik.maknaBatik
            tvHarga.text = "Rp${batik.hargaRendah}-Rp${batik.hargaTinggi}"
            tvKota.text =
                if (batik.daerahBatik == "" || batik.daerahBatik == "-") "Tidak dikenali" else batik.daerahBatik
            Glide.with(applicationContext)
                .load(batik.imgBatik)
                .placeholder(R.drawable.logo_transparent)
                .error(R.drawable.logo_transparent)
                .into(imgBatik)
            if (batik.isFavorite!!) {
                isFavoriteBatik(true)
            } else {
                isFavoriteBatik(false)
            }
            var isFav = batik.isFavorite
            btnFavorite.setOnClickListener {
                isFav = isFav==false
                isFav!!.let {
                    isFavoriteBatik(it)
                    viewModel.setFav(batik, it)
                }
            }
        }
    }

    private fun isFavoriteBatik(state: Boolean) {
        if (state) {
            Log.d("TAG", "STAR")
            binding.apply {
                btnFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_star_24
                    )
                )
            }
        } else {
            Log.d("TAG", "INSTAR")
            binding.apply {
                btnFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_outline_star_outline_24
                    )
                )
            }
        }
    }
}