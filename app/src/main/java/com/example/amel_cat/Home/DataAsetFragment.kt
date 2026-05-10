package com.example.amel_cat.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.amel_cat.R
import com.example.amel_cat.Home.TambahAsetFragment
import com.example.amel_cat.databinding.FragmentDataAsetBinding
import com.google.android.material.chip.Chip

class DataAsetFragment : Fragment() {

    private var _binding: FragmentDataAsetBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDataAsetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                .addToBackStack(null) // Agar bisa tombol Back
                .commit()
        }
        binding.toolbarDataAset.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}