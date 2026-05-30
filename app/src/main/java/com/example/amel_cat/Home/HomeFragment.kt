package com.example.amel_cat.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amel_cat.Home.tugasp10.MutasiAset
import com.example.amel_cat.Home.tugasp2.SecondActivity
import com.example.amel_cat.Home.tugasp3.LoginActivity
import com.example.amel_cat.Home.tugasp6.WebViewActivity
import com.example.amel_cat.R
import com.example.amel_cat.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

// IMPORT UNTUK RETROFIT PICSUM YANG BARU
import com.example.amel_cat.Data.Model.PhotoModel
import com.example.amel_cat.Data.Api.PhotoApiClient
import com.example.amel_cat.Home.Photo.PhotoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        // ==========================================
        // SETUP RECYCLERVIEW UNTUK DATA RETROFIT
        // ==========================================
        // Pastikan di fragment_home.xml sudah ada RecyclerView dengan id: rvPhoto
        binding.rvPhoto.layoutManager = LinearLayoutManager(context)

        // Panggil fungsi mengambil data dari API Picsum
        loadPhotos()

        // ==========================================
        // LOGIKA TOMBOL-TOMBOL BAWAAN KAMU
        // ==========================================

        // Tombol 1
        binding.btnRumus.setOnClickListener {
            val intent = Intent(requireActivity(), SecondActivity::class.java)
            intent.putExtra("JUDUL", "Kalkulator Bangun Ruang")
            intent.putExtra("DESKRIPSI", "Hitung luas segitiga dan volume balok")
            startActivity(intent)
        }

        binding.btnWebView.setOnClickListener {
            val intent = Intent(requireActivity(), WebViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnDataAset.setOnClickListener {
            val fragment = DataAsetFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.btnUser.setOnClickListener {
            val fragment = UserFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.btnLokasi.setOnClickListener {
            val intent = Intent(requireActivity(), MutasiAset::class.java)
            startActivity(intent)
        }

        // Tombol 4: Logout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireActivity()).apply {
                setTitle("Konfirmasi Logout")
                setMessage("Apakah Anda yakin ingin keluar?")
                setPositiveButton("Ya") { _, _ ->
                    sharedPref.edit().clear().apply()

                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                    requireActivity().finish()
                }
                setNegativeButton("Tidak") { _, _ ->
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                show()
            }
        }
    }

    // ==========================================
    // FUNGSI RETROFIT UNTUK LOAD DATA PICSUM
    // ==========================================
    private fun loadPhotos() {
        PhotoApiClient.instance.getPhotos().enqueue(object : Callback<List<PhotoModel>> {
            override fun onResponse(
                call: Call<List<PhotoModel>>,
                response: Response<List<PhotoModel>>
            ) {
                if (response.isSuccessful) {
                    val photos = response.body()
                    if (photos != null) {
                        // Masukkan list data dari API ke adapter kustom kamu
                        val adapter = PhotoAdapter(photos)
                        binding.rvPhoto.adapter = adapter
                    }
                } else {
                    Toast.makeText(context, "Gagal memuat gambar API", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                Toast.makeText(context, "Error Koneksi API: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}