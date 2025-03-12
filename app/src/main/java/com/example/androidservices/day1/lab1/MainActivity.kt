package com.example.androidservices.day1.lab1

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Suppress("PreviewAnnotationInFunctionWithParameters")
class MainActivity : ComponentActivity() {

    private var myService = mutableStateOf <DateTimeService?>(null)
    private var isBound = mutableStateOf(false)

    private val serviceConnection= object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as DateTimeService.MyBinder
            myService.value = binder.getService()
            isBound.value = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound.value = false
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, DateTimeService::class.java)
        bindService(intent,serviceConnection, BIND_AUTO_CREATE)


        //enableEdgeToEdge()
        setContent {
            LocalBoundScreen(myService=myService.value)

        }


    }

    @Preview (showSystemUi = true)
    @Composable
    private fun LocalBoundScreen(myService: DateTimeService?) {
        val currentDateTime = rememberSaveable() { mutableStateOf("Press button to get current date and time ") }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Text(text = currentDateTime.value)

              Button(onClick = {myService?.let { currentDateTime.value = it.getCurrentDateTime ()  }  } )
              {
                  Text("Get current date and time")
              }

        }




    }
}


