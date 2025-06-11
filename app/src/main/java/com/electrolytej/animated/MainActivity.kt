package com.electrolytej.animated

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.electrolytej.animated.databinding.ActivityMainBinding
import com.electrolytej.pag.PagDrawable
import com.electrolytej.svg.SvgSoftwareLayerSetter

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
        binding.ivImage.loadUrl("https://test-sc.seeyouyima.com/eimg/adimg/2022/6/62ac2bbfa17b1_640_300.gif")
        Glide.with(this)
//            .asFrameSequence()
//            .load(R.raw.fire)
            .load("https://test-sc.seeyouyima.com/eimg/adimg/2022/6/62ac2bbfa17b1_640_300.gif")
//            .transform(FitCenter())
            .into(binding.iv)
        Glide.with(this)
//            .`as`(PagDrawable::class.java)
            .load("file:///android_asset/b.pag")
//            .transform(FitCenter())
            .into(binding.ivPag)

        Glide.with(this)
            .`as`(LottieDrawable::class.java)
            .load("file:///android_asset/17902-covid19.json")
//            .transform(FitCenter())
            .into(binding.ivLottie)

        Glide.with(this)
            .`as`(PictureDrawable::class.java)
//            .transform(FitCenter())
//            .transition(withCrossFade())
            .listener(SvgSoftwareLayerSetter())
            .load(R.raw.android_toy_h)
            .into(binding.ivSvg)


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
}