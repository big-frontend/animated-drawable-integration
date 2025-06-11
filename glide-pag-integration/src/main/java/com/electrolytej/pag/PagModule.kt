package com.electrolytej.pag

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.LibraryGlideModule
import org.libpag.PAGFile
import java.io.InputStream

@GlideModule
class PagModule : LibraryGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.register(
            PAGFile::class.java, PagDrawable::class.java,
            PagDrawableTranscoder()
        ).append(
//            Registry.BUCKET_ANIMATION,
            InputStream::class.java,
            PAGFile::class.java,
            PagDecoder(context, registry.imageHeaderParsers, glide.bitmapPool, glide.arrayPool)
        )
    }
}