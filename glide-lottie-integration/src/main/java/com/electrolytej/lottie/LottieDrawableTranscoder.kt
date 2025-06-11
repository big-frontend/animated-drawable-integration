package com.electrolytej.lottie

import android.util.Log
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.drawable.DrawableResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder

class LottieDrawableTranscoder : ResourceTranscoder<LottieComposition, LottieDrawable> {
    override fun transcode(
        toTranscode: Resource<LottieComposition>, options: Options
    ): Resource<LottieDrawable> {
        Log.d("LottieDecoder", "transcode")
        val lottieResult = toTranscode.get()
        val lottieDrawable = LottieDrawable()
        lottieDrawable.setComposition(lottieResult)
        lottieDrawable.repeatCount = LottieDrawable.INFINITE
        return LottieDrawableResource(lottieDrawable)
    }

    class LottieDrawableResource(drawable: LottieDrawable) :
        DrawableResource<LottieDrawable>(drawable) {
        override fun getResourceClass(): Class<LottieDrawable> {
            return LottieDrawable::class.java
        }

        override fun getSize(): Int {
//            return drawable.size
            //todo caculate size
            return 0
        }

        override fun recycle() {
            drawable.stop()
            drawable.cancelAnimation()
//            drawable.destroy()
        }
    }
}
