package com.example.amel_cat.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amel_cat.Data.Entity.Aset
import com.example.amel_cat.databinding.ItemAsetBinding

class AsetAdapter(private var listAset: List<Aset>) : RecyclerView.Adapter<AsetAdapter.ViewHolder>() {

    // ADDED: Properti callback untuk menangani klik dari Fragment
    var onItemClickListener: ((Aset) -> Unit)? = null

    class ViewHolder(val binding: ItemAsetBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAsetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aset = listAset[position]
        holder.binding.tvNamaAset.text = aset.namaAset
        holder.binding.tvJenisAset.text = aset.jenisAset
        holder.binding.tvJumlahAset.text = "Jumlah: ${aset.jumlahAset}"

        // ADDED: Alur klik ketika item aset (meja, kursi, laptop, hp) ditekan oleh admin
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(aset)
        }
    }

    override fun getItemCount(): Int = listAset.size

    fun updateData(newList: List<Aset>) {
        listAset = newList
        notifyDataSetChanged()
    }
}