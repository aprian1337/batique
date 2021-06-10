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
import com.aprian1337.core.ui.adapters.BatikAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { BatikAdapter() }
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        viewModel.batik.observe(viewLifecycleOwner, {
            when (it) {
                is com.aprian1337.core.data.Status.LOADING -> {
                    showLoading(true)
                }
                is com.aprian1337.core.data.Status.SUCCESS -> {
                    it.data?.apply {
                        adapter.setBatik(this)
                    }
                    showLoading(false)
                }
                is com.aprian1337.core.data.Status.ERROR -> {
                    showLoading(false)
                }
            }
        })
        adapter.setOnItemClickCallback(object : BatikAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Batik) {
                selectedUser(data)
            }
        })
    }

    private fun selectedUser(data: Batik) {
        Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_NAMA_BATIK, data.namaBatik)
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

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.apply {
                rvBatik.visibility = View.GONE
                pbHome.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                rvBatik.visibility = View.VISIBLE
                pbHome.visibility = View.GONE
            }
        }
    }
    
}