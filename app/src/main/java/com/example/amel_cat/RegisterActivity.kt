package com.example.amel_cat

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// PERBAIKAN IMPORT: Pastikan mengarah ke package yang benar sesuai folder kamu
import com.example.amel_cat.ValidasiActivity
import com.example.amel_cat.databinding.ActivityRegisterBinding
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var tanggalLahir = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Logika DatePicker untuk Tanggal Lahir
        binding.etRegTanggal.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, yearSelected, monthOfYear, dayOfMonth ->
                tanggalLahir = "$dayOfMonth/${monthOfYear + 1}/$yearSelected"
                binding.etRegTanggal.setText(tanggalLahir)
            }, year, month, day)
            dpd.show()
        }

        // 2. Klik Tombol Selanjutnya
        binding.btnRegister.setOnClickListener {
            val nama = binding.etRegNama.text.toString().trim()
            val email = binding.etRegEmail.text.toString().trim()
            val username = binding.etRegUsername.text.toString().trim()
            val password = binding.etRegPassword.text.toString().trim()
            val confirmPass = binding.etRegConfirmPassword.text.toString().trim()

            // Ambil data RadioButton (Jenis Kelamin)
            val selectedGenderId = binding.rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId == binding.rbLaki.id) "Laki-laki" else "Perempuan"

            // Validasi input
            if (nama.isNotEmpty() && email.isNotEmpty() && tanggalLahir.isNotEmpty() &&
                username.isNotEmpty() && password.isNotEmpty()) {

                if (password != confirmPass) {
                    Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // --- SIMPAN KE SHAREDPREFERENCE (Memori Permanen) ---
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("reg_nama", nama)
                editor.putString("reg_email", email)
                editor.putString("reg_tanggal", tanggalLahir)
                editor.putString("reg_gender", gender)
                editor.putString("reg_username", username)
                editor.apply()

                // --- TAMBAHAN: KIRIM DATA LEWAT INTENT (Paling Penting!) ---
                val intent = Intent(this, ValidasiActivity::class.java)
                intent.putExtra("EXTRA_NAMA", nama)
                intent.putExtra("EXTRA_EMAIL", email)
                intent.putExtra("EXTRA_TANGGAL", tanggalLahir)
                intent.putExtra("EXTRA_GENDER", gender)
                intent.putExtra("EXTRA_USERNAME", username)

                startActivity(intent)

            } else {
                Toast.makeText(this, "Harap isi semua data!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}