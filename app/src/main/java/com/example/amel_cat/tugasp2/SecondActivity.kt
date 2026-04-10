package com.example.amel_cat.tugasp2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.amel_cat.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val etPanjang = findViewById<EditText>(R.id.etPanjang)
        val etLebar = findViewById<EditText>(R.id.etLebar)
        val etTinggiBalok = findViewById<EditText>(R.id.etTinggiBalok)
        val btnSegitiga = findViewById<Button>(R.id.btnSegitiga)
        val btnBalok = findViewById<Button>(R.id.btnBalok)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        btnSegitiga.setOnClickListener {
            val alas = etAlas.text.toString()
            val tinggi = etTinggi.text.toString()

            if (alas.isEmpty() || tinggi.isEmpty()) {
                Toast.makeText(this, "Isi dulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val a = alas.toDouble()
            val t = tinggi.toDouble()

            if (a <= 0 || t <= 0) {
                Toast.makeText(this, "Angka positif", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hasil = 0.5 * a * t
            tvHasil.text = "Luas Segitiga = $hasil"
        }

        btnBalok.setOnClickListener {
            val panjang = etPanjang.text.toString()
            val lebar = etLebar.text.toString()
            val tinggi = etTinggiBalok.text.toString()

            if (panjang.isEmpty() || lebar.isEmpty() || tinggi.isEmpty()) {
                Toast.makeText(this, "Isi dulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val p = panjang.toDouble()
            val l = lebar.toDouble()
            val t = tinggi.toDouble()

            if (p <= 0 || l <= 0 || t <= 0) {
                Toast.makeText(this, "Angka positif", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hasil = p * l * t
            tvHasil.text = "Volume Balok = $hasil"
        }
    }
}