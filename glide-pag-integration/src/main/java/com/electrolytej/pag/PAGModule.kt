package com.electrolytej.pag

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.LibraryGlideModule
import java.io.InputStream

@GlideModule
class PAGModule : LibraryGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.prepend(
            Registry.BUCKET_ANIMATION,
            InputStream::class.java,
            PAGDrawable::class.java,
            StreamPagDecoder(
                context,
                registry.imageHeaderParsers, glide.bitmapPool, glide.arrayPool)
        )
    }
}