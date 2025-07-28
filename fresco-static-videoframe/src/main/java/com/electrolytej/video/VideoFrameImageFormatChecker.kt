package com.electrolytej.video

import com.facebook.imageformat.ImageFormat

class VideoFrameImageFormatChecker : ImageFormat.FormatChecker {
    companion object {
        val VIDEO_FRAME_FORMAT = ImageFormat("VIDEO", ".mp4")
        private val VIDEO_HEADER = "ftyp".toByteArray()
    }

    //一般 12 个字节能确定。MP4 文件起始位置通常是 “ftyp” 类型的 Box，用于标志文件类型。该 Box 的头部包含 4 字节的长度信息和 4 字节的类型标识 “ftyp”，
    // 再加上后面表示主品牌的 4 字节，共 12 个字节，可判断文件为 MP4 格式
    override val headerSize: Int
        get() = 12

    override fun determineFormat(headerBytes: ByteArray, headerSize: Int): ImageFormat {
        val headerJsonStr = headerBytes.decodeToString(4, 8)
        if ("ftyp".contentEquals(headerJsonStr)) {
            return VIDEO_FRAME_FORMAT
        }
        return ImageFormat.UNKNOWN
    }

}