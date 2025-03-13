package com.example.androidservices.day2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DownloadReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val imagePath = intent.getStringExtra("imagePath")
        Log.d("DownloadReceiver", "Download complete: $imagePath")
        imagePath?.let {
            val displayIntent = Intent(context, ImageViewerActivity::class.java).apply {
                putExtra("imagePath", it)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(displayIntent)
        }
    }
}