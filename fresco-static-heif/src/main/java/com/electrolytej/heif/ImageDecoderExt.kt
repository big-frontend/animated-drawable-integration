package com.electrolytej.heif

import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.os.Build
import java.io.File

fun getHeifImageFromSdcardUseImageDecoder(path: String): Drawable {
    val file = File(path)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val source = ImageDecoder.createSource(file)
        return ImageDecoder.decodeDrawable(source)
    } else {
        TODO("VERSION.SDK_INT < P")
    }

}