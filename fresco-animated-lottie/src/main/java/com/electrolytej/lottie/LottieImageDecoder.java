package com.electrolytej.lottie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.facebook.imageformat.ImageFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.LottieResult;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;
import java.io.IOException;
import java.io.InputStream;

public class LottieImageDecoder implements ImageDecoder {
    public LottieImageDecoder(Context context) {
    }

    @Nullable
    @Override
    public CloseableImage decode(@NonNull EncodedImage encodedImage, int length, @NonNull QualityInfo qualityInfo, @NonNull ImageDecodeOptions imageDecodeOptions) {
        try {
            Log.e("LottieImageDecoder", "decode "+encodedImage.getSource());
            InputStream inputStream = encodedImage.getInputStream();
            if (inputStream == null) return null;
            LottieResult<LottieComposition> lottieCompositionLottieResult = LottieCompositionFactory.fromJsonInputStreamSync(inputStream,encodedImage.getSource());
            final LottieComposition composition = lottieCompositionLottieResult.getValue();
            if (composition == null) {
                throw new IOException("Failed to parse Lottie composition");
            }
            LottieDrawable lottieDrawable = new LottieDrawable();
            lottieDrawable.setComposition(composition);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    lottieDrawable.playAnimation();
                }
            });
            Rect bounds = composition.getBounds();
            Log.e("LottieImageDecoder", "decode ：" + bounds.width() + "/" + bounds.height());
            Log.e("LottieImageDecoder", "decode ：" + lottieDrawable.getIntrinsicWidth() + "/" + lottieDrawable.getIntrinsicHeight());
            return new CloseableLottieImage(lottieDrawable, composition);
        } catch (Exception e) {
            Log.e("LottieImageDecoder", "Error decoding Lottie image", e);
            throw new RuntimeException(e);
        }
    }
}