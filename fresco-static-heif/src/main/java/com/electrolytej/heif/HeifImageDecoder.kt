package com.electrolytej.heif

import android.graphics.Bitmap
import com.aliyun.libheif.HeifInfo
import com.aliyun.libheif.HeifNative
import com.facebook.common.internal.Preconditions
import com.facebook.common.references.CloseableReference
import com.facebook.imageformat.DefaultImageFormats
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.decoder.ImageDecoder
import com.facebook.imagepipeline.image.BaseCloseableStaticBitmap
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.image.EncodedImage
import com.facebook.imagepipeline.image.QualityInfo
import com.facebook.imagepipeline.memory.BitmapCounterProvider

class HeifImageDecoder : ImageDecoder {

    override fun decode(
        encodedImage: EncodedImage,
        length: Int,
        qualityInfo: QualityInfo,
        options: ImageDecodeOptions
    ): CloseableImage? {

//
//        val bufferFromFile = ByteBufferUtil.fromFile(file)
//        val bytes = ByteBufferUtil.toBytes(bufferFromFile)
//        val heifInfo = HeifInfo()
//        HeifNative.getInfo(heifInfo, file.length(), bytes)
//        val heifSize = heifInfo.frameList[0]
//        val bitmap =
//            Bitmap.createBitmap(heifSize.width, heifSize.height, Bitmap.Config.ARGB_8888)
//
//        HeifNative.toRgba(file.length(), bytes, bitmap)
//        return HeifBitmap(
//            pinBitmap(bitmap),
//            qualityInfo,
//            encodedImage.rotationAngle,
//            encodedImage.exifOrientation
//        )
        return null
    }

    private fun pinBitmap(bitmap: Bitmap): CloseableReference<Bitmap> {
        return CloseableReference.of(
            Preconditions.checkNotNull(bitmap), BitmapCounterProvider.get().releaser
        )
    }

    class HeifBitmap(
        bitmapReference: CloseableReference<Bitmap>?,
        qualityInfo: QualityInfo?,
        rotationAngle: Int,
        exifOrientation: Int
    ) : BaseCloseableStaticBitmap(bitmapReference, qualityInfo, rotationAngle, exifOrientation) {
    }
}
