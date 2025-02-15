package com.daiatech.chitralekhan.app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.daiatech.chitralekhan.ChitraLekhanCanvas
import com.daiatech.chitralekhan.models.DrawMode
import com.daiatech.chitralekhan.rememberChitraLekhan

@Composable
fun App(modifier: Modifier = Modifier) {
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    val mediaPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            context.contentResolver.openInputStream(uri)?.use { `is` ->
                imageBitmap = BitmapFactory.decodeStream(`is`)
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            imageBitmap?.let { bmp ->
                val chitraLekhan = rememberChitraLekhan(
                    image = bmp,
                    drawMode = DrawMode.FreeHand
                )
                ChitraLekhanCanvas(chitraLekhan = chitraLekhan, modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        if (chitraLekhan.drawMode.value == DrawMode.FreeHand) {
                            chitraLekhan.setDrawMode(DrawMode.None)
                        } else {
                            chitraLekhan.setDrawMode(DrawMode.FreeHand)
                        }
                    }
                ) {
                    Text(if (chitraLekhan.drawMode.value == DrawMode.FreeHand) "Enable Zoom" else "Enable Draw")
                }
            } ?: run {
                Row {
                    Button(onClick = { mediaPicker.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)) }) {
                        Text("Pick image")
                    }

                }
            }
        }
    }
}