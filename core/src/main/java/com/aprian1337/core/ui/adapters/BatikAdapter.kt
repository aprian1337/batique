package com.aprian1337.core.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.core.R
import com.aprian1337.core.databinding.ItemRowBatikBinding
import com.aprian1337.core.domain.model.Batik
import com.bumptech.glide.Glide

class BatikAdapter : RecyclerView.Adapter<BatikAdapter.MainViewHolder>() {

    private var batikData = mutableListOf<Batik>()

    private var setOnItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.setOnItemClickCallback = onItemClickCallback
    }

    inner class MainViewHolder(val binding: ItemRowBatikBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setBatik(listBatik: List<Batik>) {
        batikData.clear()
        batikData = listBatik.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            ItemRowBatikBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val batik = batikData[position]
        holder.binding.let {
            it.tvItemName.text = batik.namaBatik
            it.tvItemCity.text =
                if (batik.daerahBatik == "" || batik.daerahBatik == "-") "Tidak dikenali" else batik.daerahBatik
            Glide.with(it.root.context)
                .load(batik.imgBatik)
                .placeholder(R.drawable.logo_transparent)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(it.imgItemPhoto)
            it.root.setOnClickListener {
                setOnItemClickCallback?.onItemClicked(batik)
            }
        }
    }

    override fun getItemCount(): Int = batikData.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Batik)
    }
}