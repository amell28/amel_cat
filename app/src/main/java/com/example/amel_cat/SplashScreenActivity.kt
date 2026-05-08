package com.example.amel_cat

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.amel_cat.Home.tugasp3.LoginActivity
import com.example.amel_cat.tugasp4.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Pindahkan SEMUA logika perpindahan ke dalam lifecycleScope
        lifecycleScope.launch {
            // 1. Tampilkan logo/splash selama 2 detik
            delay(2000)

            // 2. Baru ambil data shared preferences
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            // 3. Tentukan arah halaman
            if (isLogin) {
                // Jika sudah login, ke MainActivity
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Jika belum login, ke LoginActivity
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            // 4. Tutup SplashScreen agar tidak bisa di-back
            finish()
        }
    }
}