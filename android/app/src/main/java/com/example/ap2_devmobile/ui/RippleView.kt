package com.example.ap2_devmobile.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View

class RippleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }

    private val ripples = mutableListOf<Float>()
    private val handler = Handler(Looper.getMainLooper())
    private var centerX = 0f
    private var centerY = 0f

    private val runnable = object : Runnable {
        override fun run() {
            ripples.add(0f)
            if (ripples.size > 5) ripples.removeAt(0)
            invalidate()
            handler.postDelayed(this, 800)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = w / 2f
        centerY = h / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val maxRadius = width.coerceAtLeast(height) * 0.8f

        ripples.forEachIndexed { index, _ ->
            ripples[index] = ripples[index] + 6f
            val radius = ripples[index]
            val alpha = (1f - radius / maxRadius).coerceIn(0f, 1f)
            paint.alpha = (alpha * 80).toInt()
            paint.color = 0xFFFF2D78.toInt()
            canvas.drawCircle(centerX, centerY, radius, paint)
        }

        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        handler.post(runnable)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacks(runnable)
    }
}