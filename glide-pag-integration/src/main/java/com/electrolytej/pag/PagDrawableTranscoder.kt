package com.electrolytej.pag

import android.util.Log
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.drawable.DrawableResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import org.libpag.PAGFile

class PagDrawableTranscoder : ResourceTranscoder<PAGFile, PagDrawable> {
    override fun transcode(
        toTranscode: Resource<PAGFile>, options: Options
    ): Resource<PagDrawable>? {
        Log.d("PagDecoder", "transcode")
        val pagFile: PAGFile = toTranscode.get()
//        val picture: Picture = svg.renderToPicture()
//        val drawable: PictureDrawable = PictureDrawable(picture)
//        return SimpleResource<PictureDrawable>(drawable)
        return null
    }

    class PAGDrawableResource(drawable: PagDrawable) : DrawableResource<PagDrawable>(drawable) {
        override fun getResourceClass(): Class<PagDrawable> {
            return PagDrawable::class.java
        }

        override fun getSize(): Int {
//            return drawable.size
            //todo caculate size
            return  0
        }

        override fun recycle() {
//            drawable.stop()
//            drawable.destroy()
        }
    }
}
