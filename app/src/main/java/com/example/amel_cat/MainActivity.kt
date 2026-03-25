package com.example.amel_cat

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var etAlas: EditText
    lateinit var etTinggi: EditText
    lateinit var etPanjang: EditText
    lateinit var etLebar: EditText
    lateinit var etTinggiBalok: EditText
    lateinit var btnSegitiga: Button
    lateinit var btnBalok: Button
    lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etAlas = findViewById(R.id.etAlas)
        etTinggi = findViewById(R.id.etTinggi)
        etPanjang = findViewById(R.id.etPanjang)
        etLebar = findViewById(R.id.etLebar)
        etTinggiBalok = findViewById(R.id.etTinggiBalok)
        btnSegitiga = findViewById(R.id.btnSegitiga)
        btnBalok = findViewById(R.id.btnBalok)
        tvHasil = findViewById(R.id.tvHasil)

        btnSegitiga.setOnClickListener {
            hitungSegitiga()
        }

        btnBalok.setOnClickListener {
            hitungBalok()
        }
    }

    fun hitungSegitiga() {
        val alas = etAlas.text.toString()
        val tinggi = etTinggi.text.toString()

        if (alas == "" || tinggi == "") {
            Toast.makeText(this, "Isi semua", Toast.LENGTH_SHORT).show()
            return
        }

        val a = alas.toDouble()
        val t = tinggi.toDouble()

        if (a <= 0 || t <= 0) {
            Toast.makeText(this, "Angka harus positif", Toast.LENGTH_SHORT).show()
            return
        }

        val luas = 0.5 * a * t
        tvHasil.text = "Luas Segitiga = $luas cm2"
    }

    fun hitungBalok() {
        val panjang = etPanjang.text.toString()
        val lebar = etLebar.text.toString()
        val tinggi = etTinggiBalok.text.toString()

        if (panjang == "" || lebar == "" || tinggi == "") {
            Toast.makeText(this, "Isi semua", Toast.LENGTH_SHORT).show()
            return
        }

        val p = panjang.toDouble()
        val l = lebar.toDouble()
        val t = tinggi.toDouble()

        if (p <= 0 || l <= 0 || t <= 0) {
            Toast.makeText(this, "Angka harus positif", Toast.LENGTH_SHORT).show()
            return
        }

        val volume = p * l * t
        tvHasil.text = "Volume Balok = $volume cm3"
    }
}