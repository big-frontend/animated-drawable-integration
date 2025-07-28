package com.electrolytej.animated

import android.graphics.Bitmap
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.electrolytej.animated.databinding.ActivityMainBinding
import com.electrolytej.svg.SvgSoftwareLayerSetter
import com.facebook.animated.gif.GifFrame
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        loadAvif()
        loadHeif()
        loadSvg()
        loadGif()
        loadPag()
        loadLottie()
        loadApng()
        loadVideo()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun onGlideGif(view: View) {}
    fun onGlidePag(view: View) {}
    fun onGlideLottie(view: View) {}
    fun onGlideSvg(view: View) {}
    fun onGlideHeic(view: View) {}
    fun onFrescoHeic(view: View) {}

    fun loadAvif() {
        binding.ivAvifFresco.loadUrl("asset:///abandoned_filmgrain.avif")
        Glide.with(this)
//            .`as`(PagDrawable::class.java)
            .load("file:///android_asset/abandoned_filmgrain.avif")
            .into(binding.ivAvifGlide)
    }

    fun loadHeif() {
        binding.ivHeicFresco.loadUrl("asset:///example.heic")
        Glide.with(this)
//            .transform(FitCenter())
//            .transition(withCrossFade())
            .load("file:///android_asset/example.heic")
            .into(binding.ivHeicGlide)
    }

    fun loadSvg() {
        Glide.with(this)
            .`as`(PictureDrawable::class.java)
//            .transform(FitCenter())
//            .transition(withCrossFade())
            .listener(SvgSoftwareLayerSetter())
            .load(R.raw.android_toy_h)
            .into(binding.ivSvgGlide)
    }

    fun loadGif() {
        binding.ivGifFresco.loadUrl("https://test-sc.seeyouyima.com/eimg/adimg/2022/6/62ac2bbfa17b1_640_300.gif")
//        binding.ivGifFresco.loadUrl("asset:///car.gif")
        Glide.with(this)
            .asGif()
//            .load(R.raw.car)
            .load("https://test-sc.seeyouyima.com/eimg/adimg/2022/6/62ac2bbfa17b1_640_300.gif")
            .addListener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable,
                    model: Any,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    if (isFirstResource) {
                        val iv = ImageView(this@MainActivity)
                        val first = resource.firstFrame
                        iv.setImageBitmap(Bitmap.createBitmap(first))
                        binding.fbl.addView(iv, ViewGroup.LayoutParams(240, 240))
                    }
                    return false
                }

            })
            .into(binding.ivGifGlide)
//        Glide.with(this)
////            .asFrameSequence()
//            .`as`(FrameSequenceDrawable::class.java)
////            .transform(FitCenter())
////            .load(R.raw.fire)
////            .load("file:///android_asset/car.gif")
//            .load("https://test-sc.seeyouyima.com/eimg/adimg/2022/6/62ac2bbfa17b1_640_300.gif")
//            .addListener(object :RequestListener<FrameSequenceDrawable>{
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<FrameSequenceDrawable>,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Log.d("cjf","onLoadFailed:${isFirstResource} ")
//                    return false
//                }
//                override fun onResourceReady(
//                    resource: FrameSequenceDrawable,
//                    model: Any,
//                    target: Target<FrameSequenceDrawable>?,
//                    dataSource: DataSource,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Log.d("cjf","onResourceReady:${isFirstResource} ${resource}")
//                    val iv = ImageView(this@MainActivity)
//                    iv.setImageDrawable(resource)
//                    binding.fbl.addView(iv, ViewGroup.LayoutParams(240,240))
//                    if (isFirstResource){
//                    }
//                    return false
//                }
//
//            })
//            .into(binding.ivGifGlide)
    }

    fun loadPag() {
        binding.ivPagFresco.loadUrl("asset:///b.pag")
        Glide.with(this)
//            .`as`(PagDrawable::class.java)
            .load("file:///android_asset/b.pag")
//            .transform(FitCenter())
            .into(binding.ivPagGlide)
    }

    fun loadLottie() {
        binding.ivLottieFresco.loadUrl("asset:///17902-covid19.json")
        Glide.with(this)
            .`as`(LottieDrawable::class.java)
            .load("file:///android_asset/17902-covid19.json")
//            .transform(FitCenter())
            .into(binding.ivLottieGlide)
    }

    private fun loadApng() {
        binding.ivApngFresco.loadUrl("asset:///shake.png")
        Glide.with(this)
//            .`as`(PagDrawable::class.java)
            .load("file:///android_asset/shake.png")
//            .load("file:///android_asset/b.pag")
//            .transform(FitCenter())
            .into(binding.ivApngGlide)
    }
    private fun loadVideo() {
//        binding.ivVideoFresco.loadUrl("https://ad-evods.tianya.tv/evideo/2025/7/6867aacf2122b.mp4")
        binding.ivVideoFresco.loadUrl("asset:///video.mp4")
//        binding.ivVideoFresco.loadUrl(R.raw.video)
//        binding.ivVideoFresco.loadFirstFrameVideo("https://ad-evods.tianya.tv/evideo/2025/7/6867aacf2122b.mp4")
        Glide.with(this)
//            .`as`(PagDrawable::class.java)
//            .load("https://ad-evods.tianya.tv/evideo/2025/7/6867aacf2122b.mp4")
            .load("file:///android_asset/video.mp4")
//            .load(R.raw.video)
//            .transform(FitCenter())
            .into(binding.ivVideoGlide)
    }
}