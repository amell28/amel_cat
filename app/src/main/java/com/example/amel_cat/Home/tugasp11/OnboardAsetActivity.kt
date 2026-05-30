package com.example.amel_cat.Home.tugasp11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.amel_cat.databinding.ActivityOnboardAsetBinding

// 1. NAMA CLASS-NYA HARUS ONBOARD ASET, BUKAN BINDING!
class OnboardAsetActivity : AppCompatActivity() {

    // 2. Di sini baru bener pakai ActivityOnboardAsetBinding
    private lateinit var binding: ActivityOnboardAsetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menerapkan view binding sesuai instruksi modul langkah ke-3
        binding = ActivityOnboardAsetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Membuat daftar fragment (hanya 2 fragment onboarding milikmu)
        val fragmentsList = listOf<Fragment>(
            OnboardAset1Fragment(),
            OnboardAset2Fragment()
        )

        // Memasukkan activity (this) dan daftar fragment ke dalam adapter kustom kamu
        val adapter = AsetFragmentAdapter(this, fragmentsList)

        // Memasang adapter ke ViewPager2 dengan ID sesuai modul: tutorialMessageViewPager
        binding.tutorialMessageViewPager.adapter = adapter

        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
    }
}