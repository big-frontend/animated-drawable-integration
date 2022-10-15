package com.electrolye.framesequence

import android.rastermill.FrameSequenceDrawable
import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.ImageHeaderParserUtils
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
import com.bumptech.glide.load.resource.gif.GifOptions
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer

class StreamFrameSequenceDecoder(
    val parsers: List<ImageHeaderParser>,
    val byteBufferDecoder: ByteBufferFrameSequenceDecoder,
    val byteArrayPool: ArrayPool
) : ResourceDecoder<InputStream, FrameSequenceDrawable> {
    override fun handles(source: InputStream, options: Options) =
        options.get(GifOptions.DISABLE_ANIMATION) == false && ImageHeaderParserUtils.getType(
            parsers, source, byteArrayPool
        ) == ImageHeaderParser.ImageType.GIF

    override fun decode(
        source: InputStream, width: Int, height: Int, options: Options
    ): Resource<FrameSequenceDrawable>? {
        val data = inputStreamToBytes(source) ?: return null
        val byteBuffer = ByteBuffer.wrap(data)
        return byteBufferDecoder.decode(byteBuffer, width, height, options)
    }

    private fun inputStreamToBytes(`is`: InputStream): ByteArray? {
        val bufferSize = 16384
        val buffer = ByteArrayOutputStream(bufferSize)
        return try {
            var nRead: Int
            val data = ByteArray(bufferSize)
            while (`is`.read(data).also { nRead = it } != -1) {
                buffer.write(data, 0, nRead)
            }
            buffer.flush()
            buffer.toByteArray()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
