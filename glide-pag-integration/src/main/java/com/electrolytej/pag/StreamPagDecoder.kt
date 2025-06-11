package com.electrolytej.pag

import android.content.Context
import android.util.Log
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
import org.libpag.PAGFile
import java.io.InputStream

class StreamPagDecoder(
    val context: Context,
    val parsers: List<ImageHeaderParser>,
    val bitmapPool: BitmapPool,
    val arrayPool: ArrayPool
) : ResourceDecoder<InputStream, PAGDrawable> {

    override fun handles(source: InputStream, options: Options) =
        options.get(GifOptions.DISABLE_ANIMATION) == false && ImageHeaderParserUtils.getType(
            parsers, source, arrayPool
        ) == ImageType.UNKNOWN


    override fun decode(
        source: InputStream,
        width: Int,
        height: Int,
        options: Options
    ): Resource<PAGDrawable>? {
        Log.d("PagDecoder", "decode")
        val pagFile = PAGFile.Load(source.readBytes())
//        val pagFile = PAGFile.Load(context.getAssets(), "alpha.pag")

//        return PAGDrawableResource(PAGDrawable())
        return null
    }

    class PAGDrawableResource(drawable: PAGDrawable) : DrawableResource<PAGDrawable>(drawable) {
        override fun getResourceClass(): Class<PAGDrawable> {
            return PAGDrawable::class.java
        }

        override fun getSize(): Int {
//            return drawable.size
            //todo caculate size
            return  0
        }

        override fun recycle() {
//            drawable.stop()
//            drawable.destroy()
        }
    }
}