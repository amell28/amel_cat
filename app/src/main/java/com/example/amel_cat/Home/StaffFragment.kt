// C:/Users/LEGION/StudioProjects/amel_cat/app/src/main/java/com/example/amel_cat/Home/StaffFragment.kt
package com.example.amel_cat.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amel_cat.Data.AppDatabase
import com.example.amel_cat.R
import com.example.amel_cat.databinding.FragmentStaffBinding
import kotlinx.coroutines.launch

class StaffFragment : Fragment() {
    private var _binding: FragmentStaffBinding? = null
    private val binding get() = _binding!!
    private lateinit var staffAdapter: StaffAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStaffBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        staffAdapter = StaffAdapter(emptyList())
        binding.rvStaff.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStaff.adapter = staffAdapter

        lifecycleScope.launch {
            AppDatabase.getDatabase(requireContext()).staffDao().getAllStaff().collect {
                staffAdapter.updateData(it)
            }
        }

        binding.fabTambahStaff.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TambahStaffFragment())
                .addToBackStack(null).commit()
        }

        binding.toolbarStaff.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}