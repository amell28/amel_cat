package com.example.amel_cat.Home.tugasp11

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AsetFragmentAdapter(
    activity: FragmentActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(activity) {

    // Menghitung jumlah fragment yang ada di dalam list (isinya 2)
    override fun getItemCount(): Int {
        return fragments.size
    }

    // Menampilkan fragment sesuai posisi slide saat digeser
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}