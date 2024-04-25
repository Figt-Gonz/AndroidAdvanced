package com.example.androidadvanced.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    View(context, attrs, defStyleAttr, defStyleRes) {

    private var lastX = 0
    private var lastY = 0

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = x
                lastY = y
            }
            MotionEvent.ACTION_MOVE -> {
                val offsetX = x - lastX
                val offsetY = y - lastY
                moveViewByLayoutParams(offsetX, offsetY)
            }
        }
        return true
    }

    private fun moveViewByLayoutParams(offsetX: Int, offsetY: Int) {
//        val lp = layoutParams as LinearLayout.LayoutParams
        val lp = layoutParams as ViewGroup.MarginLayoutParams // 可实现同样的效果
        lp.leftMargin = left + offsetX
        lp.topMargin = top + offsetY
        layoutParams = lp
    }

    private fun moveViewByLayout(offsetX: Int, offsetY: Int) {
        layout(
            left + offsetX,
            top + offsetY,
            right + offsetX,
            bottom + offsetY
        )
    }
}