package com.chum_6ucket.giphyparser.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.chum_6ucket.giphyparser.adapters.GifsAdapter
import com.chum_6ucket.giphyparser.databinding.FragmentTrendingBinding
import com.chum_6ucket.giphyparser.viewmodels.TrendingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding
    private val viewModel: TrendingViewModel by viewModels()
    private var fetchGifsJob: Job? = null
    private var adapter: GifsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        context ?: return binding.root

        setupAdapter()
        setupSearchView()
        fetchGifs()
        setupTrendingRecyclerView()

        binding.gifList.adapter = adapter

        return binding.root
    }


    private fun setupAdapter() {
        adapter = GifsAdapter()
    }

    private fun setupSearchView() {
        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.onSearch(text.toString())
        }

        viewModel.searchText.observe(viewLifecycleOwner, Observer { query ->
            fetchGifs(query)
        })
    }

    private fun setupTrendingRecyclerView() {
        binding.gifList.apply {
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun fetchGifs(query: String = "") {
        fetchGifsJob?.cancel()
        fetchGifsJob = lifecycleScope.launch {
            delay(300)
            viewModel.fetchGifs(query).collectLatest {
                adapter?.submitData(it)
            }
        }
    }
}