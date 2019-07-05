package com.martdev.android.draganddrawkotlin

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class BoxDrawingView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    //main constructor is used when inflating the view from XML

    //second constructor is used when creating the view in code

    constructor(context: Context) : this(context, attrs = null)

    companion object {
        private const val TAG = "BoxDrawingView"
    }

    private var currentBox: Box? = null
    private var boxes: MutableList<Box> = ArrayList()
    //Paint the boxes a nice semitransparent red
    private val boxPaint: Paint = Paint().apply { color = 0x22ff0000 }

    //Paint the background off-white
    private val backgroundPaint: Paint = Paint().apply { color = 0xfff8efe0.toInt() }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val current = PointF(event?.x!!, event.y)

        val action = when(event.action) {
            MotionEvent.ACTION_DOWN -> firstAction(current)
            MotionEvent.ACTION_MOVE -> secondAction(current)
            MotionEvent.ACTION_UP -> thirdAction()
            MotionEvent.ACTION_CANCEL -> fourthAction()
            else -> "NO ACTION"
        }
        Log.i(TAG, "$action at x= ${current.x}, y = ${current.y}")

        return true
    }

    override fun onDraw(canvas: Canvas?) {
        //Fill the background
        canvas?.drawPaint(backgroundPaint)

        for (box in boxes) {
            val left = Math.min(box.origin.x, box.current.x)
            val right = Math.max(box.origin.x, box.current.x)
            val top = Math.min(box.origin.y, box.current.y)
            val bottom = Math.max(box.origin.y, box.current.y)

            canvas?.drawRect(left, top, right, bottom, boxPaint)
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val parcel = super.onSaveInstanceState()

        return Bundle().apply {
            putParcelable("Parcel", parcel)
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var currentState: Parcelable? = null
        if (state is Bundle) {
            currentState = state.getParcelable("Parcel")
        }
        super.onRestoreInstanceState(currentState)
    }

    private fun firstAction(current: PointF): String {
        currentBox = Box(current)
        boxes.add(currentBox!!)
        Log.i(TAG, "Number of box is ${boxes.size}")
        return "ACTION_DOWN"
    }

    private fun secondAction(current: PointF): String {
        if (currentBox != null) {
            currentBox!!.current = current
            invalidate()
        }
        return "ACTION_MOVE"
    }

    private fun thirdAction(): String {
        currentBox = null
        return "ACTION_UP"
    }

    private fun fourthAction(): String {
        currentBox = null
        return "ACTION_CANCEL"
    }
}