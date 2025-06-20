package com.electrolytej.lottie

import android.content.Context
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.LibraryGlideModule
import java.io.InputStream


@GlideModule
class LottieModule : LibraryGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.register(
            LottieComposition::class.java, LottieDrawable::class.java,
            LottieDrawableTranscoder()
        ).append(
//            Registry.BUCKET_ANIMATION,
            InputStream::class.java,
            LottieComposition::class.java,
            LottieDecoder(registry.imageHeaderParsers, glide.bitmapPool, glide.arrayPool)
        )
    }
}