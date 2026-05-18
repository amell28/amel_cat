package com.example.amel_cat.Home.tugasp10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amel_cat.R
import com.example.amel_cat.databinding.FragmentPemindahanBinding

class Pemindahan : Fragment() {

    private var _binding: FragmentPemindahanBinding? = null
    private val binding get() = _binding!!

    // 1. Tambah data dummy khusus Pemindahan Aset
    private val pemindahanList = listOf(
        MutasiModel("M001", "Laptop ASUS ExpertBook B5", "Ruang IT Server ➔ Ruang Direksi", "18 Mei 2026", "https://els.id/wp-content/uploads/2025/02/ExpertBook-P1-P1403-5.png"),
        MutasiModel("M002", "Kursi Kerja Ergonomis", "Gudang Utama ➔ Ruang HRD", "17 Mei 2026", "https://www.interio.co.id/sites/default/files/styles/large/public/images/product/kursi-kerja-kursi-kantor-office-chair-black-winfield/winfield-black-web.jpg?itok=19UaobFg"),
        MutasiModel("M003", "Monitor LG 24 Inch", "Divisi Pemasaran ➔ Divisi Kreatif", "16 Mei 2026", "https://www.lg.com/content/dam/channel/wcms/id/images/monitor/22mr410-b_atiq_eain_id_c/gallery/large03.jpg"),
        MutasiModel("M004", "Printer Epson L3110", "Ruang Administrasi ➔ Loket Pendaftaran", "15 Mei 2026", "https://elevenkomputer.com/13676-large_default/printer-epson-l3550-multifungsi-printer-wifi.jpg"),
        MutasiModel("M005", "Proyektor BenQ W1070", "Ruang Rapat Utama ➔ Ruang Kelas A", "14 Mei 2026", "https://down-id.img.susercontent.com/file/d103393972ea118170693574a397244c"),
        MutasiModel("M006", "Meja Kerja Minimalis", "Gudang B ➔ Ruang Magang", "12 Mei 2026", "https://rumahmebel.id/wp-content/uploads/2022/10/Meja-Kerja-Sudut-Minimalis-Warna-Hitam.jpg"),
        MutasiModel("M007", "Router WiFi TP-Link", "Koridor Lantai 1 ➔ Lab Komputer", "10 Mei 2026", "https://www.linksys.com/cdn/shop/articles/Tech_Brief_Router_white_48682d98-f68c-451f-910e-b3b15e33dec0.png?v=1763435065&width=1080"),
        MutasiModel("M008", "Dispenser Air Sharp", "Pantry Utama ➔ Ruang Tunggu VIP", "09 Mei 2026", "https://image.made-in-china.com/202f0j00RYsbvfLtgOoq/Standing-LCD-Display-Water-Cooler-Water-Dispenser-Ylrs-B13.webp"),
        MutasiModel("M009", "Scanner Fujitsu ScanSnap", "Ruang Arsip ➔ Ruang Keuangan", "08 Mei 2026", "https://hetero.co.id/storage/2022/12/SP-1130N.jpg"),
        MutasiModel("M010", "AC Split Samsung 1 PK", "Ruang Meeting Kecil ➔ Ruang Istirahat", "05 Mei 2026", "https://aquaelektronik.com/upload_files/1/56445b50c2-ac-split.png")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 2. Ubah cara inflate-nya agar View Binding aktif dengan benar
        _binding = FragmentPemindahanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 3. Inisialisasi MutasiAdapter dengan list data di atas
        val adapter = MutasiAdapter(pemindahanList) { selectedItem ->
            Toast.makeText(requireContext(), "Melihat: ${selectedItem.assetName}", Toast.LENGTH_SHORT).show()
        }

        // 4. Pasang adapter ke RecyclerView (Pastikan ID di XML-mu adalah rvPemindahan)
        binding.rvPemindahan.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}