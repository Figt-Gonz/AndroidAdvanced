package com.example.androidadvanced.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Scroller

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    View(context, attrs, defStyleAttr, defStyleRes) {

    private val scroller = Scroller(context)
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
                // todo 为什么下面这种 scrollTo() 效果跟 scrollBy 不一样？
                val v = (parent as View)
//                scrollTo(v.scrollX - offsetX, v.scrollY - offsetY)// 无法使得 View 跟随手指移动
                v.scrollBy(-offsetX, -offsetY)// 可以使得 View 随手指移动
            }
        }
        return true
    }

    override fun computeScroll() {
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            (parent as View).scrollTo(scroller.currX, scroller.currY)
            invalidate()
        }
    }

    fun smoothScrollTo(destX: Int, destY: Int) {
        val deltaX = destX - scrollX
        val deltaY = destY - scrollY
        scroller.startScroll(scrollX, scrollY, deltaX, deltaY, 2000)
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