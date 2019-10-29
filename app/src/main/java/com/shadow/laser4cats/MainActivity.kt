package com.shadow.laser4cats

import android.graphics.Point
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private var currX = 0F
    private var currY = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        val coordinate = getRandomCoordinates()
        laser?.setCenterCoordinates(coordinate.first, coordinate.second)
        laser?.visibility = View.VISIBLE
    }

    private fun getRandomCoordinates(): Pair<Float, Float> {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val maxWidth = size.x
        val maxHeight = size.y
        currX = kotlin.random.Random.nextInt(0, maxWidth - 40).toFloat()
        currY = kotlin.random.Random.nextInt(0, maxHeight - 40).toFloat()

        return Pair(currX, currY)
    }



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            val eventX = event.x
            val eventY = event.y
            val distance =
                distance(currX.toDouble(), currY.toDouble(), eventX.toDouble(), eventY.toDouble())

            if (distance <= 100 || event.pointerCount > 1) {
                laser?.visibility = View.GONE
                val coordinate = getRandomCoordinates()
                laser?.setCenterCoordinates(coordinate.first, coordinate.second)
                laser?.visibility = View.VISIBLE
            }
        }
        return true
    }

    private fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
        return sqrt((x2 - x1).pow(2.0) + (y2 - y1).pow(2.0))
    }
}
