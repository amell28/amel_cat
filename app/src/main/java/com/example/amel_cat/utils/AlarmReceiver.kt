package com.example.amel_cat.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.amel_cat.DetailAsetActivity

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // 1. Menangkap payload data yang dikirim oleh AlarmManager
        val title = intent.getStringExtra("EXTRA_TITLE") ?: "Pengingat Inventaris"
        val message = intent.getStringExtra("EXTRA_MESSAGE") ?: "Waktu audit aset telah tiba."
        val barangId = intent.getStringExtra("EXTRA_BARANG_ID") ?: ""

        // 2. ALUR KLIK: Arahkan ke DetailAsetActivity (Halaman yang relevan)
        val detailIntent = Intent(context, DetailAsetActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("EXTRA_BARANG_ID", barangId)
        }

        // 3. Memicu helper untuk memunculkan local notification
        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = detailIntent
        )
    }
}
