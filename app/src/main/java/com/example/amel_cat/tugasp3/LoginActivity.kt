package com.example.amel_cat.tugasp3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.amel_cat.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init viewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // klik tombol login
        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // validasi email
            if (email.isEmpty()) {
                binding.etEmail.error = "Email wajib diisi"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }

            // validasi password
            if (password.isEmpty()) {
                binding.etPassword.error = "Password wajib diisi"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            // sukses → pindah ke Dashboard
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)

            // optional: biar tidak bisa balik ke login
            finish()

            Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
        }

        // klik forgot password
        binding.tvForgot.setOnClickListener {
            Toast.makeText(this, "Fitur belum tersedia", Toast.LENGTH_SHORT).show()
        }

        // checkbox remember me (opsional)
        binding.cbRemember.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Remember Me aktif", Toast.LENGTH_SHORT).show()
            }
        }
    }
}