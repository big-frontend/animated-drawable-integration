package com.electrolytej.lottie

import android.annotation.SuppressLint
import android.util.Base64
import android.util.Log
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.parser.moshi.JsonReader
import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.SimpleResource
import okio.Okio
import java.io.IOException
import java.io.InputStream
import java.security.MessageDigest


/**
 * 资源解码：讲流数据转为LottieComposition
 */
class LottieDecoder(
    val parsers: List<ImageHeaderParser>, val bitmapPool: BitmapPool, val arrayPool: ArrayPool
) : ResourceDecoder<InputStream, LottieComposition> {

    override fun handles(source: InputStream, options: Options) = true

    @SuppressLint("RestrictedApi")
    override fun decode(
        source: InputStream, width: Int, height: Int, options: Options
    ): Resource<LottieComposition>? {
        Log.d("LottieDecoder", "decode")
        val cacheKey = cachedKey(source)
        val jsonReader = JsonReader.of(Okio.buffer(Okio.source(source)))
        val lottie = LottieCompositionFactory.fromJsonReaderSync(jsonReader, cacheKey)?.value
        if (lottie != null) {
            return SimpleResource(lottie)
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
                messageDigest.digest(bytes), Base64.URL_SAFE or Base64.NO_WRAP
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

}