package com.electrolytej.pag

import android.graphics.Bitmap
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo
import com.facebook.imagepipeline.animated.base.AnimatedImage
import com.facebook.imagepipeline.animated.base.AnimatedImageFrame
import org.libpag.PAGFile

class PagImage(
    val pagFile: PAGFile
) : AnimatedImage {
    override fun dispose() {
        TODO("Not yet implemented")
    }

    override fun getWidth() = pagFile.width()

    override fun getHeight() = pagFile.height()

    override fun getFrameCount() = pagFile.numImages()

    override fun getDuration() = pagFile.duration().toInt()

    override fun getFrameDurations(): IntArray {
        TODO("Not yet implemented")
    }

    override fun getLoopCount(): Int {
        return 1
    }

    override fun getFrame(frameNumber: Int): AnimatedImageFrame {
        TODO("Not yet implemented")
    }

    override fun doesRenderSupportScaling(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getSizeInBytes(): Int {
        TODO("Not yet implemented")
    }

    override fun getFrameInfo(frameNumber: Int): AnimatedDrawableFrameInfo {
        TODO("Not yet implemented")
    }

    override fun getAnimatedBitmapConfig(): Bitmap.Config? {
        TODO("Not yet implemented")
    }
}