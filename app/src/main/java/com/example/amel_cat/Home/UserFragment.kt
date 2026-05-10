package com.example.amel_cat.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.amel_cat.R
import com.example.amel_cat.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    // Data simulasi sesuai dengan web Bina Desa
    private val userList = listOf(
        UserModel("Siti Amelia Larasati", "siti24si@mahasiswa.pcr.ac.id", "Admin", R.drawable.profile),
        UserModel("Irfan Nashiruddin", "febi56@example.com", "Staff", "https://i.pravatar.cc/150?img=3"),
        UserModel("Darman Rajasa", "luthfi.nasyiah@example.com", "Staff", "https://i.pravatar.cc/150?img=67"),
        UserModel("Kanda Simanjuntak", "hairyanto@example.com", "Kades", "https://i.pravatar.cc/150?img=65"),
        UserModel("Garang Zulkarnain", "restu@example.org", "Staff", "https://i.pravatar.cc/150?img=64"),
        UserModel("Mila Lestari", "lala90@example.org", "Kades", "https://i.pravatar.cc/150?img=5")
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    // TAMBAHKAN INI BOS
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi Adapter
        val adapter = UserAdapter(requireContext(), userList)

        // 2. Pasang adapter ke ListView
        binding.listViewUser.adapter = adapter

        // 3. AKSI SAAT ITEM DIKLIK (Tambahin ini bos)
        binding.listViewUser.setOnItemClickListener { _, _, position, _ ->
            val user = userList[position]
            android.widget.Toast.makeText(
                requireContext(),
                "Detail user: ${user.userName}",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }

        // 4. Pasang fungsi back pada toolbar
        binding.toolbarUser.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


