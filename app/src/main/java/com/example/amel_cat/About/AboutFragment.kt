package com.example.amel_cat.About

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.amel_cat.Home.tugasp13.CameraActivity
import com.example.amel_cat.Home.tugasp13.GenerateQRActivity
import com.example.amel_cat.Home.tugasp13.ScanQRActivity
import com.example.amel_cat.R
import com.example.amel_cat.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "About Bina Desa"
        }

        // Navigasi ke fitur P13
        binding.btnCamera.setOnClickListener {
            startActivity(Intent(requireActivity(), CameraActivity::class.java))
        }

        binding.btnScanQR.setOnClickListener {
            startActivity(Intent(requireActivity(), ScanQRActivity::class.java))
        }

        binding.btnGenerateQR.setOnClickListener {
            startActivity(Intent(requireActivity(), GenerateQRActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}