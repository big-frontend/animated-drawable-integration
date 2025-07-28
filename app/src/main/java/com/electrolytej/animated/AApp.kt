package com.electrolytej.animated

import android.app.Application
import com.electrolytej.lottie.LottieImageDecoder
import com.electrolytej.lottie.LottieImageFormatChecker
import com.electrolytej.video.VideoFrameImageDecoder
import com.electrolytej.video.VideoFrameImageFormatChecker
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.facebook.imagepipeline.decoder.ImageDecoderConfig
import okhttp3.OkHttpClient


class AApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val imageDecoderConfig = ImageDecoderConfig.newBuilder()
            .addDecodingCapability(
                LottieImageFormatChecker.LOTTIE_FORMAT,
                LottieImageFormatChecker(), LottieImageDecoder(this)
            )
            .addDecodingCapability(
                VideoFrameImageFormatChecker.VIDEO_FRAME_FORMAT,
                VideoFrameImageFormatChecker(), VideoFrameImageDecoder(this)
            )
//            .overrideDecoder(DefaultImageFormats.AVIF, AvifImageDecoder())
            .build()

        var config = OkHttpImagePipelineConfigFactory.newBuilder(this, OkHttpClient())
            .setImageDecoderConfig(imageDecoderConfig)
            .build()

//         config = ImagePipelineConfig.newBuilder(this)
//            .setImageDecoderConfig(imageDecoderConfig)
//            .setNetworkFetcher(VideoNetworkFetcher())
//            .build()
        Fresco.initialize(this, config)
    }


}