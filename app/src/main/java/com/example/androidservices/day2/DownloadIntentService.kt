package com.example.androidservices.day2

import android.app.IntentService
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URL

class DownloadIntentService : IntentService("DownloadIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        val imageUrl = intent?.getStringExtra("url") ?: return
        val bitmap = downloadImage(imageUrl)
        bitmap?.let {
            val imagePath = saveImageToInternalStorage(it)
            imagePath?.let {
                path -> sendBroadcast(path)
            }
        }

    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): String? {
        return try {
            val file = File(getExternalFilesDir(null), "downloaded_image.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            Log.i(TAG, "saveImageToInternalStorage: success ")

            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }    }


    private fun downloadImage(imageUrl: String): Bitmap? {

        return try {
            val url = URL (imageUrl)
            val connection =url.openConnection()
            connection.connect()

            val inputStream = connection.getInputStream()
            val options = BitmapFactory.Options().apply {
                inPreferredConfig = Bitmap.Config.ARGB_8888
            }


            Log.i(TAG, "downloadImage: success ")
            BitmapFactory.decodeStream(inputStream, null, options)


        }catch (e: Exception) {
            e.printStackTrace()
            Log.i(TAG, "downloadImage: fail ")

            null
        }

        

    }


    private fun sendBroadcast(imagePath: String) {
        val intent = Intent("com.example.androidservices.day2.DOWNLOAD_COMPLETE")
        intent.putExtra("imagePath", imagePath)
        Log.i(TAG, "send broad: success ")

        sendBroadcast(intent)
    }



//    private fun saveImage(input: InputStream, file: File) {
//        val output = FileOutputStream(file)
//        try {
//            try {
//                input.copyTo(output)
//            } finally {
//                input.close()
//            }
//        } finally {
//            output.close()
//        }
//    }

}


