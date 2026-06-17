package com.example.amel_cat.Home

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amel_cat.utils.AlarmReceiver
import com.example.amel_cat.Data.AppDatabase
import com.example.amel_cat.R
import com.example.amel_cat.databinding.FragmentDataAsetBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import java.util.Calendar

class DataAsetFragment : Fragment() {

    private var _binding: FragmentDataAsetBinding? = null
    private val binding get() = _binding!!
    private lateinit var asetAdapter: AsetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataAsetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // Logika saat Chip Filter diklik
        binding.chipGroupKondisi.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(requireContext(), "Filter Aktif: ${chip.text}", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Logika pindah ke halaman form tambah aset
        binding.btnTambahAset.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TambahAsetFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.toolbarDataAset.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Amati data dari Room Database
        lifecycleScope.launch {
            AppDatabase.getDatabase(requireContext()).asetDao().getAllAset().collect { listAset ->
                if (listAset.isEmpty()) {
                    binding.tvEmpty.visibility = View.VISIBLE
                    binding.rvDataAset.visibility = View.GONE
                } else {
                    binding.tvEmpty.visibility = View.GONE
                    binding.rvDataAset.visibility = View.VISIBLE

                    // 1. Mengisi data ke dalam adapter
                    asetAdapter.updateData(listAset)

                    // 2. Mengunci fungsi klik agar pop-up selalu aktif setelah database memuat data
                    asetAdapter.onItemClickListener = { asetItem ->
                        showReminderDialog(
                            context = requireContext(),
                            barangId = asetItem.id.toString(),
                            namaBarang = asetItem.namaAset
                        )
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        asetAdapter = AsetAdapter(emptyList())
        binding.rvDataAset.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = asetAdapter
        }
    }

    // =========================================================================
    // POSISI FUNGSI POP-UP DIALOG (DI DALAM BODY DATAASETFRAGMENT)
    // =========================================================================
    private fun showReminderDialog(context: Context, barangId: String, namaBarang: String) {
        val opsiMenit = arrayOf("1 Menit (Untuk Uji Coba)", "5 Menit", "15 Menit", "30 Menit")
        val nilaiMenit = arrayOf(1, 5, 15, 30)

        AlertDialog.Builder(context).apply {
            setTitle("Setel Pengingat Audit - $namaBarang")
            setItems(opsiMenit) { dialog, which ->
                val menitTerpilih = nilaiMenit[which]
                setAsetReminder(context, menitTerpilih, barangId, namaBarang)
                dialog.dismiss()
            }
            setNegativeButton("Batal", null)
            show()
        }
    }

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

        val triggerTime = Calendar.getInstance().apply {
            add(Calendar.MINUTE, minutes)
        }.timeInMillis

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
                } else {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
                }
            }
            Toast.makeText(context, "Pengingat $namaBarang aktif untuk $minutes menit lagi!", Toast.LENGTH_SHORT).show()
        } catch (e: SecurityException) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            Toast.makeText(context, "Pengingat aktif (Mode Standar)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}