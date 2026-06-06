package com.example.amel_cat.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amel_cat.Data.Entity.Staff
import com.example.amel_cat.databinding.ItemStaffBinding

class StaffAdapter(private var list: List<Staff>) : RecyclerView.Adapter<StaffAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemStaffBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemStaffBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val staff = list[position]
        holder.binding.tvNamaStaff.text = staff.namaStaff
        holder.binding.tvJabatan.text = staff.jabatan
    }

    override fun getItemCount() = list.size

    fun updateData(newList: List<Staff>) {
        list = newList
        notifyDataSetChanged()
    }
}