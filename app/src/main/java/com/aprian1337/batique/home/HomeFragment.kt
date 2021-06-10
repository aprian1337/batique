package com.aprian1337.batique.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.batique.databinding.FragmentHomeBinding
import com.aprian1337.batique.details.DetailActivity
import com.aprian1337.core.domain.model.Batik
import com.cap0097.ahuahuapp.ui.history.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { HomeAdapter() }
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        viewModel.batik.observe(viewLifecycleOwner, {
            when(it){
                is com.aprian1337.core.data.Status.LOADING -> {
                    showLoading(true)
                }
                is com.aprian1337.core.data.Status.SUCCESS -> {
                    it.data?.let {
                        adapter.setBatik(it)
                    }
                   showLoading(false)
                }
                is com.aprian1337.core.data.Status.ERROR -> {
                    showLoading(false)

                }
            }
        })
        adapter.setOnItemClickCallback(object: HomeAdapter.OnItemClickCallback{
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