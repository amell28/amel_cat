package com.example.amel_cat.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.amel_cat.Home.tugasp2.SecondActivity
import com.example.amel_cat.Home.tugasp3.LoginActivity
import com.example.amel_cat.Home.tugasp4.Custom1Activity
import com.example.amel_cat.Home.tugasp4.Custom2Activity
import com.example.amel_cat.Home.tugasp6.WebViewActivity
import com.example.amel_cat.R
import com.example.amel_cat.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) // Tambahkan super call

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        // Tombol 1
        binding.btnRumus.setOnClickListener {
            val intent = Intent(requireActivity(), SecondActivity::class.java)
            intent.putExtra("JUDUL", "Kalkulator Bangun Ruang")
            intent.putExtra("DESKRIPSI", "Hitung luas segitiga dan volume balok")
            startActivity(intent)
        }

        // Tombol 2
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(requireActivity(), Custom1Activity::class.java)
            intent.putExtra("JUDUL", "Selamat Datang di Halaman 1")
            intent.putExtra("DESKRIPSI", "Halaman pertama dengan gambar")
            startActivity(intent)
        }

        // Tombol 3
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(requireActivity(), Custom2Activity::class.java)
            intent.putExtra("JUDUL", "Selamat Datang di Halaman 2")
            intent.putExtra("DESKRIPSI", "Halaman kedua dengan gambar")
            startActivity(intent)
        }

        binding.btnWebView.setOnClickListener {
            val intent = Intent(requireActivity(), WebViewActivity::class.java)
            startActivity(intent)
        }

        // Tombol 4: Logout (BAGIAN YANG SALAH SUDAH DIPERBAIKI)
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireActivity()).apply {
                setTitle("Konfirmasi Logout")
                setMessage("Apakah Anda yakin ingin keluar?")
                setPositiveButton("Ya") { _, _ ->
                    // Hapus data sharedPref jika perlu
                    sharedPref.edit().clear().apply()

                    // Perbaikan pemanggilan Intent
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                    // Perbaikan pemanggilan finish
                    requireActivity().finish()
                }
                setNegativeButton("Tidak") { _, _ ->
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                show()
            }
        }
    }
}