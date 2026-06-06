package com.example.amel_cat.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.amel_cat.Data.AppDatabase
import com.example.amel_cat.Data.Entity.Staff
import com.example.amel_cat.databinding.FragmentTambahStaffBinding
import kotlinx.coroutines.launch

class TambahStaffFragment : Fragment() {

    private var _binding: FragmentTambahStaffBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTambahStaffBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSimpanStaff.setOnClickListener {
            val nama = binding.etNamaStaff.text.toString()
            val jabatan = binding.etJabatan.text.toString()
            val kontak = binding.etKontak.text.toString()

            if (nama.isEmpty()) {
                binding.etNamaStaff.error = "Nama tidak boleh kosong!"
            } else if (jabatan.isEmpty()) {
                binding.etJabatan.error = "Jabatan tidak boleh kosong!"
            } else {
                val newStaff = Staff(
                    namaStaff = nama,
                    jabatan = jabatan,
                    nomorKontak = kontak
                )

                lifecycleScope.launch {
                    val db = AppDatabase.getDatabase(requireContext())
                    db.staffDao().insert(newStaff)
                    
                    Toast.makeText(requireContext(), "Staff $nama berhasil disimpan!", Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }

        binding.toolbarTambahStaff.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
