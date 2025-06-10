package com.electrolyte.lottie

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.parser.moshi.JsonReader
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo
import com.facebook.imagepipeline.animated.base.AnimatedImage
import com.facebook.imagepipeline.animated.base.AnimatedImageFrame
import com.facebook.imagepipeline.animated.factory.AnimatedImageDecoder
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.image.EncodedImage
import com.facebook.imagepipeline.image.QualityInfo
import okio.Okio
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer
import java.security.MessageDigest


/**
 * 资源解码：讲流数据转为LottieDrawable
 */
class LottieImage() :AnimatedImage, AnimatedImageDecoder {

    @SuppressLint("RestrictedApi")
//    override fun decode(
//        encodedImage: EncodedImage,
//        length: Int,
//        qualityInfo: QualityInfo,
//        options: ImageDecodeOptions
//    ): CloseableImage? {
//        Log.d("LottieDecoder", "decode")
//        val source = encodedImage.getInputStream() ?: return null
//        val cacheKey = cachedKey(source)
//        val jsonReader = JsonReader.of(Okio.buffer(Okio.source(source)))
//        val lottieResult = LottieCompositionFactory.fromJsonReaderSync(jsonReader, cacheKey)
//        if (lottieResult?.value != null) {
//            val lottieDrawable = LottieDrawable()
//            lottieDrawable.setComposition(lottieResult.value)
//            lottieDrawable.repeatCount = LottieDrawable.INFINITE
//        }
//        return null
//    }

    private fun cachedKey(source: InputStream): String? {
        try {
            val length = source.available()
            val bytes = ByteArray(length)
            source.mark(length)
            source.read(bytes)
            val messageDigest = MessageDigest.getInstance("MD5")
            return Base64.encodeToString(
                messageDigest.digest(bytes),
                Base64.URL_SAFE or Base64.NO_WRAP
            )
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                source.reset()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }

    override fun decodeFromNativeMemory(
        nativePtr: Long,
        sizeInBytes: Int,
        options: ImageDecodeOptions?
    ): AnimatedImage {
        TODO("Not yet implemented")
    }

    override fun decodeFromByteBuffer(
        byteBuffer: ByteBuffer?,
        options: ImageDecodeOptions?
    ): AnimatedImage {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }

    override fun getWidth(): Int {
        TODO("Not yet implemented")
    }

    override fun getHeight(): Int {
        TODO("Not yet implemented")
    }

    override fun getFrameCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getDuration(): Int {
        TODO("Not yet implemented")
    }

    override fun getFrameDurations(): IntArray {
        TODO("Not yet implemented")
    }

    override fun getLoopCount(): Int {
        TODO("Not yet implemented")
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