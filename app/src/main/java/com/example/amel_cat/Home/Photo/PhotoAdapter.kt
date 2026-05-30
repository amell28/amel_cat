package com.example.amel_cat.Home.Photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amel_cat.databinding.ItemPhotoBinding
import com.example.amel_cat.Data.Model.PhotoModel

class PhotoAdapter(private val listPhoto: List<PhotoModel>) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: PhotoModel) {
            // 1. Menampilkan nama Author/Fotografer ke TextView
            // (Sesuaikan id TextView di item_photo.xml kamu, misalnya txtAuthor)
            binding.txtAuthor.text = photo.author

            // 2. Nge-load gambar dari downloadUrl ke ImageView menggunakan Glide
            // (Sesuaikan id ImageView di item_photo.xml kamu, misalnya imgPhoto)
            Glide.with(itemView.context)
                .load(photo.downloadUrl)
                .into(binding.imgPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPhoto[position])
    }

    override fun getItemCount(): Int = listPhoto.size
}