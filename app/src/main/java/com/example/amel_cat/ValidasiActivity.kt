package com.example.amel_cat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amel_cat.BaseActivity
import com.example.amel_cat.Home.tugasp3.LoginActivity
import com.example.amel_cat.databinding.ActivityValidasiBinding

class ValidasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityValidasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. TANGKAP DATA DARI INTENT
        val nama = intent.getStringExtra("EXTRA_NAMA")
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val tanggal = intent.getStringExtra("EXTRA_TANGGAL")
        val gender = intent.getStringExtra("EXTRA_GENDER")
        val username = intent.getStringExtra("EXTRA_USERNAME")

        // 2. TAMPILKAN KE TEXTVIEW
        binding.tvValNama.text = "Nama: $nama"
        binding.tvValEmail.text = "Email: $email"
        binding.tvValTanggal.text = "Tanggal Lahir: $tanggal"
        binding.tvValGender.text = "Jenis Kelamin: $gender"
        binding.tvValUsername.text = "Username: $username"

        // 3. LOGIKA TOMBOL KONFIRMASI (Pindah ke BaseActivity/Halaman Utama)
        binding.btnConfirm.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            // Clear task agar user tidak bisa back ke halaman registrasi lagi
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()

        }
    }
}