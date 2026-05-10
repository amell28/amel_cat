package com.example.amel_cat.Home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.amel_cat.R
import com.example.amel_cat.databinding.ItemUserBinding

class UserAdapter(
    context: Context,
    private val userList: List<UserModel>
) : ArrayAdapter<UserModel>(context, 0, userList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
        val data = userList[position]

        // 1. Load Foto (Pake 'avatar' sesuai Model lu)
        Glide.with(context)
            .load(data.avatarUrl)
            .placeholder(R.drawable.profile)
            .circleCrop()
            .into(binding.ivUserPhoto)

        // 2. Set Teks (Pake 'nama', 'email', 'status' sesuai Model lu)
        binding.tvUserName.text = data.userName
        binding.tvUserEmail.text = data.userEmail
        binding.tvStatus.text = data.status

        // 3. LOGIKA GANTI WARNA (Cek variabel data.status)
        val warnaHex = when (data.status.lowercase()) {
            "admin" -> "#00BCD4" // Biru
            "staff" -> "#4CAF50" // Hijau
            "kades" -> "#FF9800" // Oranye
            else -> "#9E9E9E"
        }

        // Ganti warna background Card-nya
        binding.cardStatus.setCardBackgroundColor(Color.parseColor(warnaHex))

        return binding.root
    }
}