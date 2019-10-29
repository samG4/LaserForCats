package com.shadow.laser4cats

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class LaserPoint(internal var context: Context, attributes: AttributeSet) :
    View(context, attributes) {
    private var X_coor = 0F
    private var Y_coor = 0F
    private val RADIUS = 30F
    private val paintInner = Paint().apply {
        color = Color.parseColor("#EB1555")
    }
    private val paintOuter = Paint().apply {
        color = Color.parseColor("#29EB1555")
    }

    fun setCenterCoordinates(x: Float, y: Float) {
        X_coor = x
        Y_coor = y
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(X_coor, Y_coor, RADIUS, paintInner)
        canvas?.drawCircle(X_coor, Y_coor, RADIUS + 5, paintOuter)
    }

}