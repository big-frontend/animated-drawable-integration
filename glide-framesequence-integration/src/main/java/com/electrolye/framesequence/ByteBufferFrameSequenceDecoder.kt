package com.electrolye.framesequence

import android.content.Context
import android.rastermill.FrameSequence
import android.rastermill.FrameSequenceDrawable
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
import java.nio.ByteBuffer

class ByteBufferFrameSequenceDecoder(
    val context: Context,
    val parsers: List<ImageHeaderParser>,
    val bitmapPool: BitmapPool,
    val arrayPool: ArrayPool
) : ResourceDecoder<ByteBuffer, FrameSequenceDrawable> {
    override fun handles(source: ByteBuffer, options: Options) =
        options.get(GifOptions.DISABLE_ANIMATION) == false && ImageHeaderParserUtils.getType(
            parsers, source
        ) == ImageType.GIF

    override fun decode(
        source: ByteBuffer, width: Int, height: Int, options: Options
    ): Resource<FrameSequenceDrawable>? {
        val frameSequence = FrameSequence.decodeByteBuffer(source)
        val drawable = FrameSequenceDrawable(frameSequence)
        Log.d("FrameSeqDecoder","decode")
        return FrameSequenceDrawableResource(drawable)
    }

    class FrameSequenceDrawableResource(drawable: FrameSequenceDrawable) : DrawableResource<FrameSequenceDrawable>(drawable) {
        override fun getResourceClass(): Class<FrameSequenceDrawable> {
            return FrameSequenceDrawable::class.java
        }

        override fun getSize(): Int {
//            return drawable.size
            //todo caculate size
            return  0
        }

        override fun recycle() {
            drawable.stop()
            drawable.destroy()
        }
    }

}
