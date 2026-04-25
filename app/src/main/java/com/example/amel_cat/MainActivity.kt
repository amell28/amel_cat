package com.example.amel_cat.tugasp4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.amel_cat.tugasp4.Custom1Activity
import com.example.amel_cat.tugasp4.Custom2Activity
import com.example.amel_cat.databinding.ActivityMainBinding
import com.example.amel_cat.tugasp2.SecondActivity
import com.example.amel_cat.tugasp3.LoginActivity
import com.example.amel_cat.tugasp6.WebViewActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "amelcat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = " Halaman Utama"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        Log.d(TAG, "onCreate: MainActivity dimulai")

        // Tombol 1: ke halaman rumus (SecondActivity di tugasp2)
        binding.btnRumus.setOnClickListener {
            Log.d(TAG, "btnRumus diklik")
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("JUDUL", "Kalkulator Bangun Ruang")
            intent.putExtra("DESKRIPSI", "Hitung luas segitiga dan volume balok")
            startActivity(intent)
        }

        // Tombol 2: ke halaman custom 1
        binding.btnCustom1.setOnClickListener {
            Log.d(TAG, "btnCustom1 diklik")
            val intent = Intent(this, Custom1Activity::class.java)
            intent.putExtra("JUDUL", "Selamat Datang di Halaman  1")
            intent.putExtra("DESKRIPSI", "Halaman pertama dengan gambar")
            startActivity(intent)
        }
        //Kode ini harus selalu dipanggil saat butuh akses "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)


        // Tombol 3: ke halaman custom 2
        binding.btnCustom2.setOnClickListener {
            Log.d(TAG, "btnCustom2 diklik")
            val intent = Intent(this, Custom2Activity::class.java)
            intent.putExtra("JUDUL", "Selamat Datang di Halaman  2")
            intent.putExtra("DESKRIPSI", "Halaman kedua dengan gambar")
            startActivity(intent)
        }
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // Tombol 4: Logout ke LoginActivity
        binding.btnLogout.setOnClickListener {
            Log.d(TAG, "btnLogout diklik")
            AlertDialog.Builder(this).apply {
                setTitle("Konfirmasi Logout")
                setMessage("Apakah Anda yakin ingin keluar?")
                setPositiveButton("Ya") { _, _ ->
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                setNegativeButton("Tidak") { _, _ ->
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                show()
            }

        }

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

    override fun onStart()
    { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume()
    { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause()
    { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop()
    { super.onStop(); Log.d(TAG, "onStop") }
    override fun onDestroy()
    { super.onDestroy(); Log.d(TAG, "onDestroy") }
}
