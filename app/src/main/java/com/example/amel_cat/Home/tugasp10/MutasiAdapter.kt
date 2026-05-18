package com.example.amel_cat.Home.tugasp10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // Import library Glide
import com.example.amel_cat.databinding.ItemMutasiBinding

class MutasiAdapter(
    private val mutationList: List<MutasiModel>,
    private val onItemClick: (MutasiModel) -> Unit
) : RecyclerView.Adapter<MutasiAdapter.MutasiViewHolder>() {

    inner class MutasiViewHolder(val binding: ItemMutasiBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MutasiViewHolder {
        val binding = ItemMutasiBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MutasiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MutasiViewHolder, position: Int) {
        val item = mutationList[position]
        with(holder.binding) {
            tvAssetName.text = item.assetName
            tvMutationDetails.text = item.details
            tvMutationDate.text = item.date

            // BARU: Memuat gambar menggunakan Glide ke ImageView (imgAsset)
            Glide.with(holder.itemView.context)
                .load(item.imageUrl) // Pastikan variabel imageUrl sudah ada di MutasiModel
                .into(imgAsset)      // ID ImageView yang ada di item_mutasi.xml kamu

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = mutationList.size
}