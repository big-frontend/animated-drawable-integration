package com.electrolytej.lottie

import android.util.Log
import com.facebook.imageformat.ImageFormat

class LottieImageFormatChecker : ImageFormat.FormatChecker {
    companion object {
        val LOTTIE_FORMAT: ImageFormat = ImageFormat("LOTTIE", "lottie")

        //{"v":"5.5.7","meta":{"g":"LottieFiles AE 0.1.20","a":"","k":"","d":"","tc":""}
        private val LOTTIE_HEADER =
            """{"v":"5.5.7","meta":{"g":"LottieFiles AE 0.1.20","a":"","k":"","d":"","tc":""}""".toByteArray()
    }

    override val headerSize: Int
        get() = LOTTIE_HEADER.size

    override fun determineFormat(
        headerBytes: ByteArray, headerSize: Int
    ): ImageFormat {
        val headerJsonStr = headerBytes.decodeToString()
        if (headerJsonStr.contains("LottieFiles")) {
            return LOTTIE_FORMAT
        }
        return ImageFormat.UNKNOWN

    }
}