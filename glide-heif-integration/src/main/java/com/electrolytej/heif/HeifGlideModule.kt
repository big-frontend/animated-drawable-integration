package com.electrolytej.heif

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.LibraryGlideModule
import java.nio.ByteBuffer

@GlideModule(glideName = "HeifGlide")
open class HeifGlideModule : LibraryGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.append(
            ByteBuffer::class.java,
            Bitmap::class.java, HeifByteBufferBitmapDecoder(glide.bitmapPool)
        )
    }
}