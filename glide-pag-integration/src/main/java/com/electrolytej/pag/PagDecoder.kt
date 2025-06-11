package com.electrolytej.pag

import android.content.Context
import android.util.Log
import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.drawable.DrawableResource
import org.libpag.PAGFile
import java.io.InputStream

class PagDecoder(
    val context: Context,
    val parsers: List<ImageHeaderParser>,
    val bitmapPool: BitmapPool,
    val arrayPool: ArrayPool
) : ResourceDecoder<InputStream, PAGFile> {

    override fun handles(source: InputStream, options: Options) = true
//        options.get(GifOptions.DISABLE_ANIMATION) == false && ImageHeaderParserUtils.getType(
//            parsers, source, arrayPool
//        ) == ImageType.UNKNOWN


    override fun decode(
        source: InputStream,
        width: Int,
        height: Int,
        options: Options
    ): Resource<PAGFile>? {
        Log.d("PagDecoder", "decode")
        val pagFile = PAGFile.Load(source.readBytes())
//        val pagFile = PAGFile.Load(context.getAssets(), "alpha.pag")
//        return PAGDrawableResource(PAGDrawable())
        return SimpleResource(pagFile)
    }

}