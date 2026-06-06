package com.example.amel_cat.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.amel_cat.Data.AppDatabase
import com.example.amel_cat.Data.Entity.Aset
import com.example.amel_cat.databinding.FragmentTambahAsetBinding
import kotlinx.coroutines.launch

class TambahAsetFragment : Fragment() {

    private var _binding: FragmentTambahAsetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTambahAsetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Di dalam onViewCreated, update bagian binding.btnSimpanAset.setOnClickListener
        binding.btnSimpanAset.setOnClickListener {
            val nama = binding.etNamaAset.text.toString()
            val kategori = binding.etKategori.text.toString()
            val nilai = binding.etNilaiAset.text.toString().toIntOrNull() ?: 0

            if (nama.isEmpty()) {
                binding.etNamaAset.error = "Nama aset tidak boleh kosong!"
            } else {
                // Simpan ke Room menggunakan Coroutines
                val newAset = Aset(
                    namaAset = nama,
                    jenisAset = kategori,
                    jumlahAset = nilai,
                    lokasiAset = "Gudang"
                )

                lifecycleScope.launch {
                    val db = AppDatabase.getDatabase(requireContext())
                    db.asetDao().insertAset(newAset)

                    Toast.makeText(requireContext(), "Aset $nama berhasil disimpan!", Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}