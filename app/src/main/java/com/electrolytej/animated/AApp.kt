package com.electrolytej.animated

import android.app.Application
import com.electrolytej.lottie.LottieImage
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory.newBuilder
import com.facebook.imagepipeline.core.ImagePipelineConfig
import okhttp3.OkHttpClient


class AApp  : Application() {
    override fun onCreate() {
        super.onCreate()

        // 配置 Fresco
        val config: ImagePipelineConfig = newBuilder(this, OkHttpClient())
//            .setImageDecoder(LottieImage())
//            .addDecoder(FrescoAvifDecoder()) // 注册 AVIF 解码器
            .build()
        Fresco.initialize(this)
    }
}