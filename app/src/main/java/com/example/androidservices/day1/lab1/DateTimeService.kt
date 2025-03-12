package com.example.androidservices.day1.lab1

import android.app.Service
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Binder
import android.os.IBinder
import java.util.Date
import java.util.Locale

class DateTimeService : Service() {

    private val binder = MyBinder()


    inner class MyBinder : Binder() {
        fun getService(): DateTimeService {
            return this@DateTimeService
        }



    }
    fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}
