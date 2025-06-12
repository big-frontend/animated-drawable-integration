package com.electrolytej.pag

import com.facebook.imagepipeline.animated.base.AnimatedImage
import com.facebook.imagepipeline.animated.factory.AnimatedImageDecoder
import com.facebook.imagepipeline.common.ImageDecodeOptions
import org.libpag.PAGFile
import java.nio.ByteBuffer

class PagImageDecoder : AnimatedImageDecoder {
    override fun decodeFromNativeMemory(
        nativePtr: Long,
        sizeInBytes: Int,
        options: ImageDecodeOptions?
    ): AnimatedImage? {
//        return PagImage()
        return null
    }

    override fun decodeFromByteBuffer(
        byteBuffer: ByteBuffer,
        options: ImageDecodeOptions?
    ): AnimatedImage? {
        val pagFile = PAGFile.Load(ByteBufferUtil.toBytes(byteBuffer))
        return PagImage(pagFile)
    }
}