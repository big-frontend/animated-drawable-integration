package com.electrolytej.lottie

import android.annotation.SuppressLint
import android.content.Context
import android.util.Base64
import android.util.Log
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.LottieListener
import com.airbnb.lottie.parser.moshi.JsonReader
import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.ImageHeaderParser.ImageType
import com.bumptech.glide.load.ImageHeaderParserUtils
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.drawable.DrawableResource
import com.bumptech.glide.load.resource.gif.GifOptions
import okio.Okio
import java.io.IOException
import java.io.InputStream
import java.security.MessageDigest


/**
 * 资源解码：讲流数据转为LottieDrawable
 */
class StreamLottieDecoder(
    val parsers: List<ImageHeaderParser>,
    val bitmapPool: BitmapPool,
    val arrayPool: ArrayPool
) : ResourceDecoder<InputStream, LottieDrawable> {

    override fun handles(source: InputStream, options: Options) =
        options.get(GifOptions.DISABLE_ANIMATION) == false && ImageHeaderParserUtils.getType(
            parsers, source,arrayPool
        ) == ImageType.UNKNOWN

    @SuppressLint("RestrictedApi")
    override fun decode(
        source: InputStream,
        width: Int,
        height: Int,
        options: Options
    ): Resource<LottieDrawable>? {
        Log.d("LottieDecoder","decode")
        val cacheKey = cachedKey(source)
        val jsonReader = JsonReader.of(Okio.buffer(Okio.source(source)))
        val lottieResult = LottieCompositionFactory.fromJsonReaderSync(jsonReader, cacheKey)
        if (lottieResult?.value != null) {
            val lottieDrawable = LottieDrawable()
            lottieDrawable.setComposition(lottieResult.value)
            lottieDrawable.repeatCount = LottieDrawable.INFINITE
            return LottieDrawableResource(lottieDrawable)
        }
        return null
    }

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

    class LottieDrawableResource(drawable: LottieDrawable) :
        DrawableResource<LottieDrawable>(drawable) {
        override fun getResourceClass(): Class<LottieDrawable> {
            return LottieDrawable::class.java
        }

        override fun getSize(): Int {
//            return drawable.size
            //todo caculate size
            return 0
        }

        override fun recycle() {
            drawable.stop()
            drawable.cancelAnimation()
//            drawable.destroy()
        }
    }
}