package com.electrolytej.animated

import android.graphics.Bitmap
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableImage

class BitmapDataSubscriber : BaseBitmapDataSubscriber() {
    override fun onNewResultImpl(bitmap: Bitmap?) {
        if (bitmap == null) return
        // 获取 AnimatedImageResult
//                    if (closeableImage is CloseableImage) {
//                        val result: AnimatedImageResult =
//                            (closeableImage as CloseableAnimatedImage).getAnimatedImageResult()
//
//
//                        // 处理所有帧
//                        val image: AnimatedImage = result.getAnimatedImage()
//                        val frameCount: Int = image.getFrameCount()
//
//
//                        // 创建处理后的帧数组
//                        val processedFrames = arrayOfNulls<Bitmap>(frameCount)
//                        val durations = IntArray(frameCount)
//
//
//                        // 遍历每一帧并应用灰度效果
//                        for (i in 0..<frameCount) {
//                            val frame: AnimatedImageFrame = image.getFrame(i)
//                            durations[i] = frame.getDurationMs()
//
//
//                            // 创建新的 Bitmap 用于处理
//                            processedFrames[i] = Bitmap.createBitmap(
//                                image.getWidth(),
//                                image.getHeight(),
//                                Bitmap.Config.ARGB_8888
//                            )
//
//
//                            // 应用灰度滤镜
//                            applyGrayScaleFilter(processedFrames[i], frame)
//                            frame.dispose()
//                        }

                // 使用处理后的帧创建动画（需要自定义 AnimatedDrawable）
//                        runOnUiThread {}
//                    }
    }

    override fun onFailureImpl(dataSource: DataSource<CloseableReference<CloseableImage?>>) {
        // 处理失败
    }
}