package com.d3if0154.listmovie.ui.daftarMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.d3if0154.listmovie.databinding.FragmentDaftarMovieBinding
import com.d3if0154.listmovie.network.DaftarMovieApi

class DaftarMovieFragment: Fragment() {

    private val viewModel: DaftarMovieViewModel by lazy {
        ViewModelProvider(this)[DaftarMovieViewModel::class.java]
    }

    private lateinit var binding: FragmentDaftarMovieBinding
    private lateinit var myAdapter: DaftarMovieAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDaftarMovieBinding.inflate(layoutInflater, container, false)
        myAdapter = DaftarMovieAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner){
            myAdapter.updateData(it)
        }
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }
    }
    private fun updateProgress(status: DaftarMovieApi.ApiStatus) {
        when (status) {
            DaftarMovieApi.ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            DaftarMovieApi.ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            DaftarMovieApi.ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}