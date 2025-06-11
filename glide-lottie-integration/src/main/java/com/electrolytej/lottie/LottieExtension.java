package com.electrolytej.lottie;

import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.request.RequestOptions;

@GlideExtension
public class LottieExtension {
    private final static RequestOptions DECODE_TYPE_FRAME_SEQUENCE =
            RequestOptions.decodeTypeOf(LottieDrawable.class).lock();

    @GlideType(LottieDrawable.class)
    public static RequestBuilder<LottieDrawable> asLottie(RequestBuilder<LottieDrawable> requestBuilder) {
        return requestBuilder.apply(DECODE_TYPE_FRAME_SEQUENCE);
    }
}