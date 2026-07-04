package com.example.amel_cat.Home

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amel_cat.utils.AlarmReceiver
import com.example.amel_cat.Home.tugasp10.MutasiAset
import com.example.amel_cat.Home.tugasp2.SecondActivity
import com.example.amel_cat.Home.tugasp3.LoginActivity
import com.example.amel_cat.Home.tugasp6.WebViewActivity
import com.example.amel_cat.R
import com.example.amel_cat.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

import com.example.amel_cat.Data.Model.PhotoModel
import com.example.amel_cat.Data.Api.PhotoApiClient
import com.example.amel_cat.Home.Photo.PhotoAdapter
import com.example.amel_cat.utils.PermissionHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val requestNotificationLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(requireContext(), "Izin Notifikasi Aktif", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Izin Notifikasi Ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        if (PermissionHelper.isNotificationPermissionRequired()) {
            if (!PermissionHelper.hasPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)) {
                PermissionHelper.requestPermission(requestNotificationLauncher, Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        binding.rvPhoto.layoutManager = LinearLayoutManager(context)
        loadPhotos()

        // Klik Menu Utama
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
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null).commit()
        }

        binding.btnUser.setOnClickListener {
            val fragment = UserFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null).commit()
        }

        binding.btnLokasi.setOnClickListener {
            startActivity(Intent(requireActivity(), MutasiAset::class.java))
        }

        binding.btnDataStaff.setOnClickListener {
            val fragment = StaffFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null).commit()
        }

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

    private fun loadPhotos() {
        PhotoApiClient.instance.getPhotos().enqueue(object : Callback<List<PhotoModel>> {
            override fun onResponse(call: Call<List<PhotoModel>>, response: Response<List<PhotoModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        binding.rvPhoto.adapter = PhotoAdapter(it)
                    }
                }
            }
            override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                Toast.makeText(context, "Error API: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}