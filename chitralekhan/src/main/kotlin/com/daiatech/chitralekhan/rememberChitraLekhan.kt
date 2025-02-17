package com.daiatech.chitralekhan

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.daiatech.chitralekhan.models.DrawMode
import com.daiatech.chitralekhan.utils.colors

@Composable
fun rememberChitraLekhan(
    color: Color,
    width: Float,
    alpha: Float = 1f,
    drawMode: DrawMode = DrawMode.FreeHand,
    image: Bitmap
): ChitraLekhan {
    return remember {
        ChitraLekhan(
            strokeColor = color,
            strokeWidth = width,
            strokeAlpha = alpha,
            drawMode = drawMode,
            image = image
        )
    }
}
