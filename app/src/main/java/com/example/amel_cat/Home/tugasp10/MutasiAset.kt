package com.example.amel_cat.Home.tugasp10

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.amel_cat.R
import com.example.amel_cat.databinding.AsetMutasiBinding
import com.google.android.material.tabs.TabLayoutMediator

class MutasiAset : AppCompatActivity() {
    private lateinit var binding: AsetMutasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = AsetMutasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            title = "Mutasi Aset"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 1. Inisialisasi Adapter
        val tabsAdapter = TenthTabsAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Atur judul untuk setiap tab
            when (position) {
                0 -> {
                    tab.text = "Pemindahan"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(  this, R.drawable.ic_pemindahan)
                    //Tambah Badge Tanpa nomor (hanya titik)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true}
                1 -> {
                    tab.text = "Penghapusan"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(  this, R.drawable.ic_penghapusan)
                    //Tambah Badge dengan nomor
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5}
            }

        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}