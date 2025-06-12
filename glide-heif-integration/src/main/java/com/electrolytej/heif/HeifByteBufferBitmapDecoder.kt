package com.electrolytej.heif

import android.graphics.Bitmap
import com.aliyun.libheif.HeifNative
import com.aliyun.libheif.HeifInfo
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.bumptech.glide.util.ByteBufferUtil
import java.nio.ByteBuffer

/**
 * https://help.aliyun.com/zh/oss/user-guide/heic-and-avif-decoding-on-android#p-h1z-0rt-1vz
 */
class HeifByteBufferBitmapDecoder(val bitmapPool: BitmapPool) :
    ResourceDecoder<ByteBuffer, Bitmap> {
    override fun handles(source: ByteBuffer, options: Options): Boolean {
        val buffer = ByteBufferUtil.toBytes(source)
        return HeifNative.isHeic(buffer.size.toLong(), buffer)
    }

    override fun decode(
        source: ByteBuffer, width: Int, height: Int, options: Options
    ): Resource<Bitmap>? {
        val buffer = ByteBufferUtil.toBytes(source)
        val heifInfo = HeifInfo()
        HeifNative.getInfo(heifInfo, buffer.size.toLong(), buffer)
        val heifSize = heifInfo.frameList[0]
        val bitmap = Bitmap.createBitmap(heifSize.width, heifSize.height, Bitmap.Config.ARGB_8888)
        HeifNative.toRgba(buffer.size.toLong(), buffer, bitmap)
        return BitmapResource.obtain(bitmap, bitmapPool)
    }
}