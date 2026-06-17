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

// IMPORT UNTUK RETROFIT PICSUM YANG BARU
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
        super.onViewCreated(view, savedInstanceState) // Tambahkan super call

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        // ADDED: Proteksi & Request Izin saat Home Pertama Kali Terbuka
        if (PermissionHelper.isNotificationPermissionRequired()) {
            if (!PermissionHelper.hasPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)) {
                PermissionHelper.requestPermission(requestNotificationLauncher, Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        binding.rvPhoto.layoutManager = LinearLayoutManager(context)



        // Panggil fungsi mengambil data dari API Picsum
        loadPhotos()

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

        binding.btnDataStaff.setOnClickListener {    val fragment = StaffFragment()
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

    // ADDED: FUNGSI REMINDER PENGINGAT MENIT BERBASIS ALARM MANAGER
    private fun setAsetReminder(context: Context, minutes: Int, barangId: String, namaBarang: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_TITLE", "Jadwal Pemeriksaan Aset Desa!")
            putExtra("EXTRA_MESSAGE", "Saatnya melakukan audit fisik untuk barang: $namaBarang.")
            putExtra("EXTRA_BARANG_ID", barangId)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            barangId.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Perhitungan mundur waktu target
        val triggerTime = Calendar.getInstance().apply {
            add(Calendar.MINUTE, minutes)
        }.timeInMillis

        try {
            // PERLINDUNGAN KHUSUS API 31+ (Termasuk API 37 yang kamu gunakan)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                    // Jika sistem memberikan izin eksekusi presisi, gunakan setExact
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
                } else {
                    // FALLBACK POLICY: Jika tidak diizinkan, gunakan alarm biasa agar aplikasi tidak ditutup paksa oleh OS
                    alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
                }
            } else {
                // Untuk Android versi lama
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
                }
            }

            // Munculkan konfirmasi sukses kepada user
            Toast.makeText(context, "Pengingat $namaBarang aktif untuk $minutes menit lagi!", Toast.LENGTH_SHORT).show()

        } catch (e: SecurityException) {
            // Melindungi jika OS tetap melempar SecurityException di API 37
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            Toast.makeText(context, "Pengingat aktif (Mode Standar akibat kebijakan OS)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}