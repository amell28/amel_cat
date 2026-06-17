package com.example.amel_cat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.amel_cat.Data.AppDatabase
import com.example.amel_cat.databinding.ActivityDetailAsetBinding
import kotlinx.coroutines.launch

class DetailAsetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAsetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAsetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Ambil ID Aset dari Intent
        val asetIdStr = intent.getStringExtra("EXTRA_BARANG_ID")
        val asetId = asetIdStr?.toIntOrNull() ?: -1

        if (asetId != -1) {
            loadDetailAset(asetId)
        } else {
            Toast.makeText(this, "ID Aset tidak valid", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnSelesaiCek.setOnClickListener {
            Toast.makeText(this, "Audit Selesai. Terima kasih!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun loadDetailAset(id: Int) {
        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(this@DetailAsetActivity)
            val aset = db.asetDao().getAsetById(id)

            if (aset != null) {
                binding.apply {
                    tvDetailNama.text = "Nama Aset: ${aset.namaAset}"
                    tvDetailJenis.text = "Jenis: ${aset.jenisAset}"
                    tvDetailJumlah.text = "Jumlah: ${aset.jumlahAset}"
                    tvDetailLokasi.text = "Lokasi: ${aset.lokasiAset}"
                }
            } else {
                Toast.makeText(this@DetailAsetActivity, "Data aset tidak ditemukan", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
