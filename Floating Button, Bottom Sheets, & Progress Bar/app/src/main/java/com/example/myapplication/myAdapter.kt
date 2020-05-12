package com.example.myapplication

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class myAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment
    {
        return when (position) {
            0 -> {
                OneFragment()
            }
            1 -> {
                TwoFragment()
            }
            else -> null
        }!!
    }

    override fun getCount(): Int
    {
        return totalTabs
    }

}