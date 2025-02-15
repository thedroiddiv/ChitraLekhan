/**
 * Copyright (c) 2025 DAIA Tech Pvt Ltd. All rights reserved.
*/

package com.daiatech.chitralekhan

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.daiatech.chitralekhan.models.DrawMode
import com.daiatech.chitralekhan.models.DrawingStroke
import com.daiatech.chitralekhan.utils.calculateDistance
import com.daiatech.chitralekhan.utils.calculateMidPoint
import com.daiatech.chitralekhan.utils.getVertices

class ChitraLekhan(
    strokeColor: Color,
    strokeWidth: Float,
    strokeAlpha: Float,
    drawMode: DrawMode,
    val enableCircle: Boolean,
    val enableRectangle: Boolean,
    val enablePolygon: Boolean,
    val enableDisabledDrawing: Boolean,
    val polygonSides: Int,
    val enableFreeHand: Boolean,
    val image: Bitmap
) {
    private val _undoList = mutableStateListOf<DrawingStroke>()
    val strokes: SnapshotStateList<DrawingStroke> get() = _undoList

    private val _drawMode = mutableStateOf(drawMode)
    val drawMode: State<DrawMode> get() = _drawMode

    private val _strokeColor = mutableStateOf(strokeColor)
    val strokeColor: State<Color> = _strokeColor

    private val _strokeWidth = mutableFloatStateOf(strokeWidth)
    val strokeWidth: State<Float> = _strokeWidth

    private val _strokeAlpha = mutableFloatStateOf(strokeAlpha)
    val strokeAlpha: State<Float> = _strokeAlpha

    private val _redoList = mutableListOf<DrawingStroke>()

    private val _originalHeight = mutableFloatStateOf(0f)
    val originalHeight: State<Float> = _originalHeight

    private val _originalWidth = mutableFloatStateOf(0f)
    val originalWidth: State<Float> = _originalWidth

    val aspectRatio = image.width.toFloat() / image.height.toFloat()
    val isPortrait = image.width < image.height

    fun updateOriginalDimensions(height: Float, width: Float) {
        _originalWidth.floatValue = width
        _originalHeight.floatValue = height
    }

    fun startDrawing(offset: Offset) {
        val stroke = when (_drawMode.value) {
            DrawMode.Circle -> DrawingStroke.Circle(
                offset,
                offset,
                strokeColor.value,
                strokeWidth.value,
                strokeAlpha.value
            )

            is DrawMode.Polygon -> {
                val edges = getVertices(0f, offset, (_drawMode.value as DrawMode.Polygon).sides)
                DrawingStroke.Polygon(
                    edges,
                    strokeColor.value,
                    strokeWidth.value,
                    strokeAlpha.value
                )
            }

            is DrawMode.FreeHand -> DrawingStroke.FreeHand(
                mutableStateListOf(offset),
                strokeColor.value,
                strokeWidth.value,
                strokeAlpha.value
            )

            is DrawMode.None -> null
            is DrawMode.Rectangle -> DrawingStroke.Rectangle(
                offset,
                offset,
                strokeColor.value,
                strokeWidth.value,
                strokeAlpha.value
            )
        }
        if (stroke != null) {
            _undoList.add(stroke)
        }
    }

    fun updateDrawing(offset: Offset) {
        if (_undoList.isEmpty()) return
        when (val lastStroke = _undoList.last()) {
            is DrawingStroke.Circle -> {
                val newCircle = DrawingStroke.Circle(
                    lastStroke.poc1,
                    offset,
                    strokeColor.value,
                    strokeWidth.value,
                    strokeAlpha.value
                )
                _undoList.removeAt(_undoList.lastIndex)
                _undoList.add(newCircle)
            }

            is DrawingStroke.FreeHand -> {
                lastStroke.points.add(offset)
            }

            is DrawingStroke.Polygon -> {
                val p1 = lastStroke.points[0]
                val p2 = offset
                val radius = calculateDistance(p1, p2) / 2
                val center = calculateMidPoint(p1, p2)
                val sides = 5
                val edges = getVertices(radius, center, sides)
                val newPolygon = DrawingStroke.Polygon(
                    edges,
                    strokeColor.value,
                    strokeWidth.value,
                    strokeAlpha.value
                )
                _undoList.removeAt(_undoList.lastIndex)
                _undoList.add(newPolygon)
            }

            is DrawingStroke.Rectangle -> {
                val newRectangle =
                    DrawingStroke.Rectangle(
                        lastStroke.d1,
                        lastStroke.d2,
                        strokeColor.value,
                        strokeWidth.value,
                        strokeAlpha.value
                    )
                _undoList.removeAt(_undoList.lastIndex)
                _undoList.add(newRectangle)
            }
        }
    }

    fun getFreeHandOffset(): Pair<List<Pair<String, List<Offset>>>, Pair<Float, Float>> {
        val freeHandOffset = mutableStateListOf<Pair<String, List<Offset>>>()
        _undoList.forEach { drawingStroke ->
            when (drawingStroke) {
                is DrawingStroke.FreeHand -> {
                    freeHandOffset.add(Pair(drawingStroke.color.toString(), drawingStroke.points))
                }

                else -> {}
            }
        }
        return Pair(freeHandOffset, Pair(originalHeight.value, originalWidth.value))
    }

    fun undo() {
        if (_undoList.isEmpty()) return
        val removed = _undoList.removeAt(_undoList.lastIndex)
        _redoList.add(removed)
    }

    fun redo() {
        if (_redoList.isEmpty()) return
        val removed = _undoList.removeAt(_undoList.lastIndex)
        _undoList.add(removed)
    }

    fun clear() {
        _undoList.clear()
        _redoList.clear()
    }

    fun setColor(color: Color) {
        _strokeColor.value = color
    }

    fun setWidth(width: Float) {
        _strokeWidth.floatValue = width
    }

    fun setAlpha(alpha: Float) {
        _strokeAlpha.floatValue = alpha
    }

    fun setDrawMode(drawMode: DrawMode) {
        _drawMode.value = drawMode
    }
}
