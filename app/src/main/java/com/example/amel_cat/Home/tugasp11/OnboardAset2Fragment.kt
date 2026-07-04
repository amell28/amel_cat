package com.example.amel_cat.Home.tugasp11

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.amel_cat.Home.tugasp3.LoginActivity
import com.example.amel_cat.databinding.FragmentOnboardAset2Binding

class OnboardAset2Fragment : Fragment() {
    private var _binding: FragmentOnboardAset2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardAset2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            // Kembali ke alur Login setelah onboarding
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)

            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
