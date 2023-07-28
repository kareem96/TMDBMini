package com.kareemdev.tmdbmini.presentation.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kareemdev.tmdbmini.presentation.detail.review.ReviewFragment
import com.kareemdev.tmdbmini.presentation.detail.trailers.TrailersFragment
import java.lang.IllegalArgumentException

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TrailersFragment()
            1 -> ReviewFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}