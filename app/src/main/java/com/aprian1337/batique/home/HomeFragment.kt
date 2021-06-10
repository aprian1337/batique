package com.aprian1337.batique.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprian1337.batique.core.data.Status
import com.aprian1337.batique.databinding.FragmentHomeBinding
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
                is Status.LOADING -> {
                    showLoading(true)
                }
                is Status.SUCCESS -> {
                    it.data?.let {
                        adapter.setBatik(it)
                    }
                   showLoading(false)
                }
                is Status.ERROR -> {
                    showLoading(false)

                }
            }
        })
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