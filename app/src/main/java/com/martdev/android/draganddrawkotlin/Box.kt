package com.martdev.android.draganddrawkotlin

import android.graphics.PointF

data class Box(private val pointF: PointF) {

    internal var origin = pointF
    internal var current = pointF
}