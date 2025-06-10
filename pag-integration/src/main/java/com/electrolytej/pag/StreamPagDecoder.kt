package com.electrolytej.pag

import android.util.Log
import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.ImageHeaderParser.ImageType
import com.bumptech.glide.load.ImageHeaderParserUtils
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.gif.GifOptions
import java.io.InputStream

class StreamPagDecoder(
    val parsers: List<ImageHeaderParser>,
    val bitmapPool: BitmapPool,
    val arrayPool: ArrayPool
) : ResourceDecoder<InputStream, PagDrawable> {

    override fun handles(source: InputStream, options: Options) =
        options.get(GifOptions.DISABLE_ANIMATION) == false && ImageHeaderParserUtils.getType(
            parsers, source, arrayPool
        ) == ImageType.UNKNOWN


    override fun decode(
        source: InputStream,
        width: Int,
        height: Int,
        options: Options
    ): Resource<PagDrawable>? {
        Log.d("PagDecoder", "decode")
        return null
    }
}