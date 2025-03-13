package com.example.androidservices.day2

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale

class ImageViewerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imagePath = intent.getStringExtra("imagePath")

        setContent {
            imagePath?.let {
                DisplayImageScreen(it)
            }
        }
    }

    @Composable
    fun DisplayImageScreen(imagePath: String) {
        val bitmap = BitmapFactory.decodeFile(imagePath)
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        DisplayImageScreen("/path/to/sample/image.jpg")
    }
}