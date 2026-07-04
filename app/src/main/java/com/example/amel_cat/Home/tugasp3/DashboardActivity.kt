package com.example.amel_cat.Home.tugasp3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amel_cat.BaseActivity
import com.example.amel_cat.R
import com.example.amel_cat.databinding.ActivityDashboardBinding
import com.example.amel_cat.Home.tugasp13.CameraActivity
import com.example.amel_cat.Home.tugasp13.GenerateQRActivity
import com.example.amel_cat.Home.tugasp13.ScanQRActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            title = "Dashboard"
            subtitle = "Selamat datang"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Tombol fitur P13
        binding.btnCamera.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        binding.btnGenerateQr.setOnClickListener {
            startActivity(Intent(this, GenerateQRActivity::class.java))
        }

        binding.btnScanQr.setOnClickListener {
            startActivity(Intent(this, ScanQRActivity::class.java))
        }

        // Tombol kembali ke login
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Tombol masuk ke MainActivity (Halaman Utama)
        binding.btnMasuk.setOnClickListener {
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
        }
    }
}