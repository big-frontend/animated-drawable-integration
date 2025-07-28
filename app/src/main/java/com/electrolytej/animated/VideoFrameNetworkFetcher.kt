package com.electrolytej.animated

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.util.Log
import com.facebook.imagepipeline.image.EncodedImage
import com.facebook.imagepipeline.producers.BaseNetworkFetcher
import com.facebook.imagepipeline.producers.Consumer
import com.facebook.imagepipeline.producers.FetchState
import com.facebook.imagepipeline.producers.NetworkFetcher
import com.facebook.imagepipeline.producers.ProducerContext
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


class VideoFrameNetworkFetcher : BaseNetworkFetcher<FetchState>() {
    override fun createFetchState(
        consumer: Consumer<EncodedImage>?,
        producerContext: ProducerContext?
    ): FetchState {
        return FetchState(consumer, producerContext)
    }

    override fun fetch(fetchState: FetchState, callback: NetworkFetcher.Callback) {
        Log.e("VideoFrameNetworkFetcher", "fetch ${fetchState.uri}")
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(fetchState.uri.toString())
            val bitmap = retriever.getFrameAtTime(0)
            val stream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 80, stream)
            val byteArray = stream.toByteArray()
            val inputStream = ByteArrayInputStream(byteArray)
            callback.onResponse(inputStream, byteArray.size)
        } catch (e: Exception) {
            e.printStackTrace()
            callback.onFailure(e)
        } finally {
            retriever.release()
        }
        // 自定义网络请求逻辑
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url(fetchState.imageRequest.sourceUri.toString())
//            .addHeader("Authorization", "Bearer token")
//            .build()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                callback.onFailure(fetchState, e)
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                callback.onResponse(fetchState, response.body?.byteStream(), -1)
//            }
//        })
    }
}