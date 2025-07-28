package com.electrolytej.lottie;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.facebook.imagepipeline.image.DefaultCloseableImage;

public class CloseableLottieImage extends DefaultCloseableImage {
    private final LottieDrawable drawable;
    private final LottieComposition composition;

    public CloseableLottieImage(LottieDrawable drawable, LottieComposition composition) {
        this.drawable = drawable;
        this.composition = composition;
    }

    @Override
    public int getWidth() {
        return composition.getBounds().width();
    }

    @Override
    public int getHeight() {
        return composition.getBounds().height();
    }

    @Override
    public int getSizeInBytes() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void close() {
        drawable.cancelAnimation();
    }

    @Override
    public boolean isClosed() {
        return !drawable.isAnimating();
    }

//    @Override
//    public LottieDrawable getUnderlyingBitmap() {
//        return drawable;
//    }
}
