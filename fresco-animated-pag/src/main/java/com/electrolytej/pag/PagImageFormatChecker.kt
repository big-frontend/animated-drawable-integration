package com.electrolytej.pag

import com.facebook.imageformat.ImageFormat

class PagImageFormatChecker : ImageFormat.FormatChecker {
    companion object {
        val PAG_FRAME_FORMAT = ImageFormat("PAG", ".pag")
        private val PAG_HEADER = "PAG".toByteArray()
    }

    override val headerSize: Int
        get() = 0

    override fun determineFormat(headerBytes: ByteArray, headerSize: Int): ImageFormat {
//        val headerJsonStr = headerBytes.decodeToString(4, 8)
//        if ("page".contentEquals(headerJsonStr)) {
//            return PAG_FRAME_FORMAT
//        }
        return ImageFormat.UNKNOWN
    }

}