package com.chum_6ucket.giphyparser.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chum_6ucket.giphyparser.adapters.HomeViewPagerAdapter
import com.chum_6ucket.giphyparser.databinding.FragmentHomeViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
        val viewPager = binding.viewPager

        viewPager.adapter = HomeViewPagerAdapter(this)
        return binding.root
    }

}