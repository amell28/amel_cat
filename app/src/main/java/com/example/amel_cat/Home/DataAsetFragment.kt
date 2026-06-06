package com.example.amel_cat.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amel_cat.Data.AppDatabase
import com.example.amel_cat.R
import com.example.amel_cat.databinding.FragmentDataAsetBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

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
                    asetAdapter.updateData(listAset)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
