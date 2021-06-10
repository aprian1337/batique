package com.cap0097.ahuahuapp.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aprian1337.batique.core.domain.model.Batik
import com.aprian1337.batique.databinding.ItemRowBatikBinding
import com.bumptech.glide.Glide

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MainViewHolder>() {

    private var batikData = mutableListOf<Batik>()

    inner class MainViewHolder(val binding: ItemRowBatikBinding) : RecyclerView.ViewHolder(binding.root)

    fun setBatik(listBatik: List<Batik>){
        batikData.clear()
        batikData = listBatik.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemRowBatikBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val batik = batikData[position]
        holder.binding.let {
            it.tvItemName.text = batik.namaBatik
            it.tvItemCity.text = batik.daerahBatik
            Glide.with(it.root.context)
                .load(batik.imgBatik)
                .into(it.imgItemPhoto)
        }
    }

    override fun getItemCount(): Int = batikData.size
}