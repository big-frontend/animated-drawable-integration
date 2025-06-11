package com.electrolytej.framesequence

import android.content.Context
import android.rastermill.FrameSequenceDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.LibraryGlideModule
import java.io.InputStream
import java.nio.ByteBuffer


@GlideModule
class FrameSequenceModule : LibraryGlideModule() {
    /**
     *
     * ResourceDecoder, 用于对新的 Resources(Drawables, Bitmaps)或新的 Data 类型(InputStreams, FileDescriptors)进行解码。
     * ResourceEncoder，用于向 Glide 的磁盘缓存写 Resources(BitmapResource, DrawableResource)。
     * Encoder, 用于向 Glide 的磁盘缓存写 Data (InputStreams, FileDesciptors)。
     * ModelLoader, 用于加载自定义的 Model(Url, Uri,任意的 POJO )和 Data(InputStreams, FileDescriptors)。
     * ResourceTranscoder，用于在不同的资源类型之间做转换，例如，从 BitmapResource 转换为 DrawableResource 。
     */
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val byteBufferFrameSequenceDecoder =
            ByteBufferFrameSequenceDecoder(context, registry.imageHeaderParsers, glide.bitmapPool, glide.arrayPool)
        registry.prepend(
                Registry.BUCKET_ANIMATION,
                InputStream::class.java,
                FrameSequenceDrawable::class.java,
                StreamFrameSequenceDecoder(registry.imageHeaderParsers, byteBufferFrameSequenceDecoder, glide.arrayPool)

            ).prepend(
                Registry.BUCKET_ANIMATION,
                ByteBuffer::class.java,
                FrameSequenceDrawable::class.java,
                byteBufferFrameSequenceDecoder
            )
    }
}