package com.electrolytej.framesequence

import android.rastermill.FrameSequenceDrawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.annotation.GlideType
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions


/**
 * Copyright ® $ 2017
 * All right reserved.
 *
 * @author: jamesfchen
 * @since: Oct/16/2022  Sun
 */
@GlideExtension
object FrameSequenceExtension {
    private val DECODE_TYPE_FRAME_SEQUENCE =
        RequestOptions.decodeTypeOf(GifDrawable::class.java).lock()

    @JvmStatic
    @GlideType(FrameSequenceDrawable::class)
    fun asFrameSequence(requestBuilder: RequestBuilder<FrameSequenceDrawable>): RequestBuilder<FrameSequenceDrawable> {
        return requestBuilder.apply(DECODE_TYPE_FRAME_SEQUENCE)
    }

    @JvmStatic
    @GlideOption
    fun squareThumb(requestOptions: BaseRequestOptions<*>): BaseRequestOptions<*> {
        return requestOptions.centerCrop()
    }
}