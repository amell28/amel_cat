package com.example.amel_cat.tugasp4

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.amel_cat.databinding.ActivityCustom2Binding

class Custom2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom2Binding
    private val TAG = "amelcat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d(TAG, "onCreate: Custom2Activity")

        binding = ActivityCustom2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val judul = intent.getStringExtra("JUDUL") ?: "Custom 2"
        val deskripsi = intent.getStringExtra("DESKRIPSI") ?: "Halaman kedua"

        Log.d(TAG, "Menerima data: judul=$judul, deskripsi=$deskripsi")

        binding.tvJudul.text = judul
        binding.tvDeskripsi.text = deskripsi

        binding.btnBack.setOnClickListener {
            Log.d(TAG, "Tombol back diklik")
            finish()
        }
    }
}