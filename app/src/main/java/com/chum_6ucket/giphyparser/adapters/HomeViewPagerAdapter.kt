package com.chum_6ucket.giphyparser.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chum_6ucket.giphyparser.views.TrendingFragment

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TrendingFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}