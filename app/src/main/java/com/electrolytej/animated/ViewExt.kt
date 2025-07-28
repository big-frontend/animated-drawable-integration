package com.electrolytej.animated
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder
import androidx.core.net.toUri

fun SimpleDraweeView?.loadUrl(resId: Int) {
    val request = ImageRequestBuilder.newBuilderWithResourceId(resId)
        .build()
    val controller = Fresco.newDraweeControllerBuilder()
        .setImageRequest(request)
        .setAutoPlayAnimations(true)
        .build()
    this?.setController(controller)
}
fun SimpleDraweeView?.loadUrl(url: String?) {
    if (url.isNullOrEmpty() || this == null) return
    val request = ImageRequestBuilder.newBuilderWithSource(url.toUri())
        .build()

//    val dataSource: DataSource<CloseableReference<CloseableImage>> = Fresco.getImagePipeline().fetchDecodedImage(request, this)
//    dataSource.subscribe(BitmapDataSubscriber(), UiThreadImmediateExecutorService.getInstance())
    val controller = Fresco.newDraweeControllerBuilder()
        .setImageRequest(request)
        .setAutoPlayAnimations(true)
        .build()
    setController(controller)
}

fun SimpleDraweeView?.loadFirstFrameVideo(url: String?) {
    if (url.isNullOrEmpty() || this == null) return
    val request =
        ImageRequestBuilder
            .newBuilderWithSource(url.toUri())
            .build()
    //setDataSourceSupplier和setImageRequest不能同时使用
    val controller = Fresco.newDraweeControllerBuilder()
        .setImageRequest(request)
//        .setDataSourceSupplier(VideoFrameDataSourceSupplier(context, url))
        .build()

    setController(controller)
}