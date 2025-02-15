package com.daiatech.chitralekhan.utils

import androidx.compose.ui.geometry.Offset
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Calculates the distance between two points using the Euclidean distance formula.
 *
 * @param offset1 The first point.
 * @param offset2 The second point.
 * @return The distance between the two points.
 */
fun calculateDistance(offset1: Offset, offset2: Offset): Float = sqrt(
    (offset1.y - offset2.y).toDouble().pow(2.0) +
            (offset2.x - offset1.x).toDouble().pow(2.0)
).toFloat()

/**
 * Calculates the midpoint between two points.
 *
 * @param offset1 The first point.
 * @param offset2 The second point.
 * @return The midpoint between the two given points.
 */
fun calculateMidPoint(offset1: Offset, offset2: Offset): Offset = Offset(
    x = (offset1.x + offset2.x) / 2,
    y = (offset1.y + offset2.y) / 2
)

/**
 * Determines the top-left corner of a rectangle given two diagonal endpoints.
 *
 * @param d1 One endpoint of the diagonal.
 * @param d2 The other endpoint of the diagonal.
 * @return The top-left corner of the rectangle.
 */
fun getTopLeft(d1: Offset, d2: Offset): Offset {
    val x1 = d1.x
    val x2 = d2.x
    val y1 = d1.y
    val y2 = d2.y
    return if (y2 > y1) {
        if (x2 > x1) {
            Offset(x1, y1)
        } else {
            Offset(x2, y1)
        }
    } else {
        if (x2 > x1) {
            Offset(x1, y2)
        } else {
            Offset(x2, y2)
        }
    }
}

/**
 * Determines the top-left and bottom-right coordinates of a rectangle given two diagonal endpoints.
 *
 * @param d1 One endpoint of the diagonal.
 * @param d2 The other endpoint of the diagonal.
 * @return A pair where the first element is the top-left corner and the second is the bottom-right corner.
 */
fun getTopLeftAndBottomRight(d1: Offset, d2: Offset): Pair<Offset, Offset> {
    val x1 = d1.x
    val x2 = d2.x
    val y1 = d1.y
    val y2 = d2.y
    return if (y2 > y1) {
        if (x2 > x1) {
            Pair(Offset(x1, y1), Offset(x2, y2))
        } else {
            Pair(Offset(x2, y1), Offset(x1, y2))
        }
    } else {
        if (x2 > x1) {
            Pair(Offset(x1, y2), Offset(x2, y1))
        } else {
            Pair(Offset(x2, y2), Offset(x1, y1))
        }
    }
}

/**
 * Generates the vertices of a regular polygon given the radius, center, and number of sides.
 *
 * @param radius The radius of the polygon.
 * @param center The center point of the polygon.
 * @param sides The number of sides of the polygon.
 * @return A list of [Offset] points representing the vertices of the polygon.
 */
fun getVertices(radius: Float, center: Offset, sides: Int): MutableList<Offset> {
    val vertices = mutableListOf<Offset>()
    val x = center.x
    val y = center.y
    for (i in 0 until sides) {
        val x1 = (x + radius * Math.cos(2 * Math.PI * i / sides)).toFloat()
        val y1 = (y + radius * Math.sin(2 * Math.PI * i / sides)).toFloat()
        vertices.add(Offset(x1, y1))
    }
    return vertices
}
