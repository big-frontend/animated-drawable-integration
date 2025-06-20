package com.electrolytej.animated

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.annotation.Nullable
import com.electrolytej.avif.AvifImageDecoder
import com.facebook.common.executors.UiThreadImmediateExecutorService
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequestBuilder
import androidx.core.net.toUri

fun SimpleDraweeView?.loadUrl(url: String?) {
    if (url.isNullOrEmpty() || this == null) return
    val request = ImageRequestBuilder.newBuilderWithSource(url.toUri())
//        .setImageDecodeOptions(ImageDecodeOptions.newBuilder().setCustomImageDecoder(
//            AvifImageDecoder()
//        ).build())
        .build()

    val dataSource: DataSource<CloseableReference<CloseableImage>> =
        Fresco.getImagePipeline().fetchDecodedImage(request, this)

    dataSource.subscribe(object : BaseBitmapDataSubscriber() {
        override fun onNewResultImpl(@Nullable bitmap: Bitmap?) {
            if (bitmap == null) return

            // 获取 AnimatedImageResult
            val imageReference = dataSource.result
            if (imageReference != null) {
                try {
                    val closeableImage = imageReference.get()
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
                } finally {
                    CloseableReference.closeSafely(imageReference)
                }
            }
        }

        override fun onFailureImpl(dataSource: DataSource<CloseableReference<CloseableImage?>>) {
            // 处理失败
        }
    }, UiThreadImmediateExecutorService.getInstance())
    val controller: DraweeController = Fresco.newDraweeControllerBuilder()
        .setImageRequest(request)
        .setAutoPlayAnimations(true) // 自动播放GIF
        .build()
    setController(controller)
}

fun SimpleDraweeView?.loadAvif(url: String?) {
    if (url.isNullOrEmpty() || this == null) return
    val request =
        ImageRequestBuilder
            .newBuilderWithSource(url.toUri())
            .setImageDecodeOptions(
                ImageDecodeOptions.newBuilder().setCustomImageDecoder(
                    AvifImageDecoder()
                ).build()
            )
            .build()
    val controller = Fresco.newDraweeControllerBuilder()
        .setImageRequest(request).build()

    setController(controller)
}

fun SimpleDraweeView?.loadPag(url: String?) {
    if (url.isNullOrEmpty() || this == null) return
    val request =
        ImageRequestBuilder
            .newBuilderWithSource(url.toUri())
//            .setImageDecodeOptions(
//                ImageDecodeOptions.newBuilder()
//                    .setCustomImageDecoder(
//                    PagImageDecoder()
//                ).build()
//            )
            .build()
    val controller = Fresco.newDraweeControllerBuilder()
        .setImageRequest(request).build()
    setController(controller)
}

fun SimpleDraweeView?.loadApng(url: String?) {
    if (url.isNullOrEmpty() || this == null) return
    val request =
        ImageRequestBuilder
            .newBuilderWithSource(url.toUri())
//            .setImageDecodeOptions(
//                ImageDecodeOptions.newBuilder().setCustomImageDecoder(
//                    AvifImageDecoder()
//                ).build()
//            )
            .build()
    val controller = Fresco.newDraweeControllerBuilder()
        .setImageRequest(request).build()

    setController(controller)
}