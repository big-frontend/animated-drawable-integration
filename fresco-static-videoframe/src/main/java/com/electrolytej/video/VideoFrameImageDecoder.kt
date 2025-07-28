package com.electrolytej.video

import android.content.Context
import android.media.MediaMetadataRetriever
import android.util.Log
import com.facebook.common.references.CloseableReference
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.decoder.ImageDecoder
import com.facebook.imagepipeline.image.BaseCloseableStaticBitmap
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.image.EncodedImage
import com.facebook.imagepipeline.image.QualityInfo
import com.facebook.imagepipeline.memory.BitmapCounterProvider
import androidx.core.net.toUri
import com.facebook.imagepipeline.image.ImmutableQualityInfo

class VideoFrameImageDecoder(val context: Context) : ImageDecoder {
    companion object {
        const val TAG = "VideoFrameImageDecoder"
    }

    override fun decode(
        encodedImage: EncodedImage,
        length: Int,
        qualityInfo: QualityInfo,
        options: ImageDecodeOptions
    ): CloseableImage? {
        val retriever = MediaMetadataRetriever()
        try {
            val imageFormat = encodedImage.imageFormat

            Log.e(TAG, "decode ${imageFormat} ${encodedImage.width}/${encodedImage.height} ${length} ${encodedImage.source}")
            val uri = encodedImage.source?.toUri()
            when (uri?.scheme) {
                "file" -> retriever.setDataSource(uri.path)
                "content" -> retriever.setDataSource(context, uri)
                "asset" -> {
                    val assetPath = uri.path?.substring(1) ?: ""
                    val fileDescriptor = context.assets.openFd(assetPath)
                    retriever.setDataSource(
                        fileDescriptor.fileDescriptor,
                        fileDescriptor.startOffset,
                        fileDescriptor.length
                    )
                }
                "http", "https" -> {
                    retriever.setDataSource(encodedImage.source)
                }
                else -> throw IllegalArgumentException("Unsupported URI scheme: ${uri?.scheme}")
            }
            val frameBitmap = retriever.frameAtTime
            Log.e(
                TAG,
                "decode ${frameBitmap} ${frameBitmap?.width}/${frameBitmap?.height} ${frameBitmap?.byteCount}"
            )
            return VideoFrameBitmap(
                CloseableReference.of(frameBitmap, BitmapCounterProvider.get().releaser),
                ImmutableQualityInfo.FULL_QUALITY,
                -1,
                -1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, Log.getStackTraceString(e))
            return null
        } finally {
            retriever.release()
        }
    }
}