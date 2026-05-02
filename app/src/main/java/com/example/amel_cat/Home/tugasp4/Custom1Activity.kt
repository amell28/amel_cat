package com.example.amel_cat.Home.tugasp4

import android.R
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.amel_cat.databinding.ActivityCustom1Binding

class Custom1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom1Binding
    private val TAG = "amelcat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d(TAG, "onCreate: Custom1Activity")

        binding = ActivityCustom1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Halaman 1"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Ambil data dari Intent
        val judul = intent.getStringExtra("JUDUL") ?: "Custom 1"
        val deskripsi = intent.getStringExtra("DESKRIPSI") ?: "Halaman pertama"

        Log.d(TAG, "Menerima data: judul=$judul, deskripsi=$deskripsi")

        binding.tvJudul.text = judul
        binding.tvDeskripsi.text = deskripsi

        binding.btnBack.setOnClickListener {
            Log.d(TAG, "Tombol back diklik")
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Custom1Activity")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Custom1Activity")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Custom1Activity")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Custom1Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Custom1Activity")
    }
}