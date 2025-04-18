package com.daiatech.chitralekhan

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.daiatech.chitralekhan.models.DrawMode

@Composable
fun rememberChitraLekhan(
    color: Color,
    width: Float,
    drawMode: DrawMode = DrawMode.FreeHand,
    image: Bitmap
): ChitraLekhan {
    return remember {
        ChitraLekhan(
            strokeColor = color,
            strokeWidth = width,
            drawMode = drawMode,
            image = image
        )
    }
}
