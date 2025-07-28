package com.electrolytej.video

import android.graphics.Bitmap
import com.facebook.common.references.CloseableReference
import com.facebook.imagepipeline.image.BaseCloseableStaticBitmap
import com.facebook.imagepipeline.image.QualityInfo

class VideoFrameBitmap(
    bitmapReference: CloseableReference<Bitmap>,
    qualityInfo: QualityInfo,
    rotationAngle: Int,
    exifOrientation: Int
) : BaseCloseableStaticBitmap(bitmapReference, qualityInfo, rotationAngle, exifOrientation)