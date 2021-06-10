package com.aprian1337.batique.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.batique.databinding.FragmentFavoriteBinding
import com.aprian1337.batique.details.DetailActivity
import com.aprian1337.batique.home.FavoriteViewModel
import com.aprian1337.core.data.Status
import com.aprian1337.core.domain.model.Batik
import com.cap0097.ahuahuapp.ui.history.BatikAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { BatikAdapter() }
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        showLoading(true)
        viewModel.batik.observe(viewLifecycleOwner, {
            it.let {
                adapter.setBatik(it as List<Batik>)
            }
            showLoading(false)
        })
        adapter.setOnItemClickCallback(object: BatikAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Batik) {
                selectedUser(data)
            }
        })
    }

    private fun selectedUser(data: Batik) {
        Intent(requireContext(), DetailActivity::class.java).apply {
            val batik = Batik(
                data.idBatik,
                data.namaBatik,
                data.daerahBatik,
                data.maknaBatik,
                data.hargaRendah,
                data.hargaTinggi,
                data.imgBatik,
                data.isFavorite,
            )
            putExtra(DetailActivity.EXTRA_DETAIL, batik)
            startActivity(this)
        }
    }

    private fun setupRv() {
        with(binding) {
            rvBatik.adapter = adapter
            rvBatik.layoutManager = LinearLayoutManager(requireContext())
            rvBatik.setHasFixedSize(true)
        }
    }

    fun showLoading(state: Boolean){
        if(state){
            binding.apply {
                rvBatik.visibility = View.GONE
                pbHome.visibility = View.VISIBLE
            }
        }else{
            binding.apply {
                rvBatik.visibility = View.VISIBLE
                pbHome.visibility = View.GONE
            }
        }
    }
}