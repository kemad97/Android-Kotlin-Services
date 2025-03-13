package com.example.androidservices.day2

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class DownloadActivityMain :ComponentActivity() {
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val receiver = DownloadReceiver()

        val intentFilter = IntentFilter("com.example.androidservices.day2.DOWNLOAD_COMPLETE")

        registerReceiver(receiver, intentFilter)
        setContent {
            DownloadScreen()
        }
    }


    @Composable
    @Preview(showSystemUi = true)
    fun DownloadScreen() {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val url = remember { mutableStateOf("https://i.pinimg.com/736x/8f/ef/aa/8fefaa44f99928585b65d34e05556590.jpg") }
            Button(onClick = {
                val intent = Intent(this@DownloadActivityMain, DownloadIntentService::class.java)
                intent.putExtra("url", url.value) // Pass the URL as a string
                startService(intent)
            }) {
                Text("Download Image")
            }
        }
    }

}