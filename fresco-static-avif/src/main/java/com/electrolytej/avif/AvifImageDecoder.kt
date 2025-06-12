package com.electrolytej.avif

import android.graphics.Bitmap
import android.util.Log
import com.facebook.common.references.CloseableReference
import com.facebook.imageformat.DefaultImageFormats
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.decoder.ImageDecoder
import com.facebook.imagepipeline.image.BaseCloseableStaticBitmap
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.image.EncodedImage
import com.facebook.imagepipeline.image.QualityInfo
import com.facebook.imagepipeline.memory.BitmapCounterProvider
import org.aomedia.avif.android.AvifDecoder
import java.nio.ByteBuffer


class AvifImageDecoder : ImageDecoder {
    override fun decode(
        encodedImage: EncodedImage,
        length: Int,
        qualityInfo: QualityInfo,
        options: ImageDecodeOptions
    ): CloseableImage? {
        val imageFormat = encodedImage.imageFormat
        Log.e(
            "AvifImageDecoder",
            "decode ${imageFormat} ${encodedImage.width}/${encodedImage.height} ${length}"
        )
        if (imageFormat != DefaultImageFormats.AVIF) return null
        try {
            var byteBuffer: ByteBuffer? = null
            encodedImage.inputStream?.use {
                byteBuffer = maybeCopyBuffer(ByteBufferUtil.fromStream(it))
            }
            if (byteBuffer == null) {
                return null
            }
            val info = AvifDecoder.Info()
            if (!AvifDecoder.getInfo(byteBuffer, byteBuffer!!.remaining(), info)) {
                Log.e(
                    "AvifImageDecoder",
                    "Requested to decode byte buffer which cannot be handled by AvifDecoder"
                )
                return null
            }
//            val config =
//                if (options.get<DecodeFormat>(Downsampler.DECODE_FORMAT) == DecodeFormat.PREFER_RGB_565) {
//                    Bitmap.Config.RGB_565
//                } else {
//                    if (info.depth == 8) {
//                        Bitmap.Config.ARGB_8888
//                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        Bitmap.Config.RGBA_F16
//                    } else {
//                        Bitmap.Config.ARGB_8888
//                    }
//                }
            val bitmap = Bitmap.createBitmap(info.width, info.height, Bitmap.Config.ARGB_8888)
            if (!AvifDecoder.decode(byteBuffer, byteBuffer!!.remaining(), bitmap)) {
                Log.e("AvifImageDecoder", "Failed to decode ByteBuffer as Avif.");
                return null
            }
            return AvifBitmap(
                CloseableReference.of(bitmap, BitmapCounterProvider.get().releaser),
                qualityInfo,
                encodedImage.rotationAngle,
                encodedImage.exifOrientation
            )
        } catch (e: Exception) {
            Log.e("AvifImageDecoder", Log.getStackTraceString(e))
        }
        return null
    }

    private fun maybeCopyBuffer(source: ByteBuffer): ByteBuffer {
        // Native calls can only access ByteBuffer if isDirect() is true. Otherwise, we would have to
        // make a copy into a direct ByteBuffer.
        if (source.isDirect) {
            return source
        }
        val sourceCopy = ByteBuffer.allocateDirect(source.remaining())
        sourceCopy.put(source)
        sourceCopy.flip()
        return sourceCopy
    }

    class AvifBitmap(
        bitmapReference: CloseableReference<Bitmap>,
        qualityInfo: QualityInfo,
        rotationAngle: Int,
        exifOrientation: Int
    ) : BaseCloseableStaticBitmap(bitmapReference, qualityInfo, rotationAngle, exifOrientation)
}