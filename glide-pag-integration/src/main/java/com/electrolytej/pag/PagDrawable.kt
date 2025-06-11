package com.electrolytej.pag

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable

class PagDrawable : Drawable(), Animatable, Runnable {
    override fun draw(p0: Canvas) {

    }

    override fun setAlpha(p0: Int) {
    }

    override fun setColorFilter(p0: ColorFilter?) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun start() {
    }

    override fun stop() {
    }

    override fun isRunning(): Boolean {
        return true
    }

    override fun run() {
    }
}