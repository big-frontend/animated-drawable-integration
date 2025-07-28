package com.electrolytej.animated

import android.content.Context
import android.media.MediaMetadataRetriever
import android.os.Looper
import android.util.Log
import androidx.core.net.toUri
import com.electrolytej.video.VideoFrameBitmap
import com.facebook.common.executors.CallerThreadExecutor
import com.facebook.common.internal.Supplier
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.AbstractDataSource
import com.facebook.datasource.DataSource
import com.facebook.datasource.DataSubscriber
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.image.ImmutableQualityInfo
import com.facebook.imagepipeline.memory.BitmapCounterProvider
import java.util.concurrent.Executor


class VideoFrameDataSourceSupplier(
    private val context: Context,
    private val videoUri: String
) : Supplier<DataSource<CloseableReference<CloseableImage>>> {
    companion object {
        const val TAG = "VideoFrameDataSource"
    }

    override fun get(): DataSource<CloseableReference<CloseableImage>> {
        return VideoFrameDataSource()
    }

    inner class VideoFrameDataSource : AbstractDataSource<CloseableReference<CloseableImage>>() {
        override fun subscribe(
            dataSubscriber: DataSubscriber<CloseableReference<CloseableImage>>,
            executor: Executor
        ) {
            Log.e(TAG, "subscribe ${isClosed} ${Thread.currentThread() == Looper.getMainLooper().thread}")
            val retriever = MediaMetadataRetriever()
            try {
                val uri = videoUri.toUri()
                when (uri.scheme) {
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
                        retriever.setDataSource(videoUri, HashMap<String, String>())
                    }

                    else -> throw IllegalArgumentException("Unsupported URI scheme: ${uri.scheme}")
                }
                val frameBitmap = retriever.getFrameAtTime(0)

                if (frameBitmap == null) {
                    dataSubscriber.onFailure(this)
                    return
                }
                Log.e(TAG, "subscribe ${frameBitmap}")

                setResult(
                    CloseableReference.of(
                        VideoFrameBitmap(
                            CloseableReference.of(
                                frameBitmap,
                                BitmapCounterProvider.get().releaser
                            ),
                            ImmutableQualityInfo.FULL_QUALITY,
                            -1, -1
                        )
                    ), true
                )
                return
            } catch (e: Exception) {
                Log.e(
                    TAG,
                    Log.getStackTraceString(e)
                )
                dataSubscriber.onFailure(this)
                CallerThreadExecutor.getInstance().execute {
                    if (!isClosed) {
                        dataSubscriber.onFailure(this)
                    }
                }
            } finally {
                retriever.release()
            }
        }
    }
}