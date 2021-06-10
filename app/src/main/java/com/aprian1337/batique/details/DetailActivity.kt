package com.aprian1337.batique.details

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.aprian1337.batique.R
import com.aprian1337.batique.databinding.ActivityDetailBinding
import com.aprian1337.core.data.Status
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAMA_BATIK = "extra_nama_batik"
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
        val batik = intent.getStringExtra(EXTRA_NAMA_BATIK)
        viewModel.getDetail(batik!!).observe(this, {
            if (it.data?.namaBatik?.isNotEmpty() == true) {
                when (it) {
                    is Status.LOADING -> {
                        showLoading(true)
                    }
                    is Status.SUCCESS -> {
                        showLoading(false)
                        it.data?.let { detail ->
                            binding.apply {
                                tvNamaBatik.text = detail.namaBatik
                                tvDeskripsi.text = detail.maknaBatik
                                tvHarga.text = "Rp${detail.hargaRendah}-Rp${detail.hargaTinggi}"
                                tvKota.text =
                                    if (detail.daerahBatik == "" || detail.daerahBatik == "-") "Tidak dikenali" else detail.daerahBatik
                                Glide.with(applicationContext)
                                    .load(detail.imgBatik)
                                    .placeholder(R.drawable.logo_transparent)
                                    .error(R.drawable.logo_transparent)
                                    .into(imgBatik)
                                if (detail.isFavorite!!) {
                                    isFavoriteBatik(true)
                                } else {
                                    isFavoriteBatik(false)
                                }
                                var isFav = detail.isFavorite
                                btnFavorite.setOnClickListener {
                                    isFav = isFav == false
                                    isFav!!.apply {
                                        isFavoriteBatik(this)
                                        viewModel.setFav(detail, this)
                                    }
                                }
                            }
                        }
                    }
                    is Status.ERROR -> {
                        showLoading(false)
                        showSnackBar(resources.getString(R.string.err_fetch))
                    }
                }
            }
        })
    }

    private fun isFavoriteBatik(state: Boolean) {
        if (state) {
            binding.apply {
                btnFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_star_24
                    )
                )
            }
        } else {
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

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.apply {
                pbDetail.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                pbDetail.visibility = View.GONE
            }
        }
    }

    private fun Activity?.showSnackBar(message: String) {
        this ?: return
        Snackbar.make(
            this.findViewById(android.R.id.content),
            message, Snackbar.LENGTH_LONG
        ).show()
    }
}