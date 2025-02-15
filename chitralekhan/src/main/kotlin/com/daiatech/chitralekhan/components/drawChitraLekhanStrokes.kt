package com.daiatech.chitralekhan.components

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.daiatech.chitralekhan.models.DrawingStroke
import com.daiatech.chitralekhan.utils.drawQuadraticBezier

fun DrawScope.drawChitraLekhanStrokes(strokes: List<DrawingStroke>) {
    strokes.forEach { stroke ->
        when (stroke) {
            is DrawingStroke.FreeHand -> {
                drawPath(
                    Path().apply { drawQuadraticBezier(stroke.points) },
                    color = stroke.color,
                    alpha = stroke.alpha,
                    style = Stroke(
                        width = stroke.width,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }

            is DrawingStroke.Polygon -> {
                drawPath(
                    Path().apply { drawQuadraticBezier(stroke.points) },
                    color = stroke.color,
                    alpha = stroke.alpha,
                    style = Stroke(
                        width = stroke.width,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }

            is DrawingStroke.Circle -> {
                drawCircle(
                    color = stroke.color,
                    radius = stroke.radius,
                    center = stroke.center,
                    style = Stroke(
                        width = stroke.width,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }

            is DrawingStroke.Rectangle -> {
                drawRect(
                    color = stroke.color,
                    topLeft = stroke.topLeft,
                    size = Size(stroke.edgeWidth, stroke.edgeLength),
                    style = Stroke(
                        width = stroke.width,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }
    }
}