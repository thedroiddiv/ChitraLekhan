package com.daiatech.chitralekhan

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.daiatech.chitralekhan.models.DrawMode
import com.daiatech.chitralekhan.utils.colors
import com.daiatech.chitralekhan.utils.thicknesses

@Composable
fun rememberChitraLekhan(
    color: Color = colors.random(),
    width: Float = thicknesses.random(),
    alpha: Float = 1f,
    drawMode: DrawMode = DrawMode.FreeHand,
    enableCircle: Boolean = false,
    enableRectangle: Boolean = false,
    enablePolygon: Boolean = false,
    enableDisabledDrawing: Boolean = true,
    polygonSides: Int = 0,
    enableFreeHand: Boolean = true,
    image: Bitmap
): ChitraLekhan {
    return remember {
        ChitraLekhan(
            strokeColor = color,
            strokeWidth = width,
            strokeAlpha = alpha,
            drawMode = drawMode,
            enableCircle = enableCircle,
            enableRectangle = enableRectangle,
            enablePolygon = enablePolygon,
            enableDisabledDrawing = enableDisabledDrawing,
            polygonSides = polygonSides,
            enableFreeHand = enableFreeHand,
            image = image
        )
    }
}
