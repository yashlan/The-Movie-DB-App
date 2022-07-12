/*
 * Created by Muhammad Yashlan Iskandar on 7/1/22, 7:56 PM
 * Last modified 6/30/22, 9:30 PM
 */

package com.yashlan.androidexpertsub1.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yashlan.androidexpertsub1.databinding.ActivityDetailWebViewMovieBinding
import com.yashlan.core.BuildConfig
import com.yashlan.core.utils.ScreenUtil

@SuppressLint("SetJavaScriptEnabled")
class DetailWebViewMovie : AppCompatActivity() {

    private lateinit var binding: ActivityDetailWebViewMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        ScreenUtil.forcePortraitScreenOrientation(this)
        ScreenUtil.setFullscreen(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailWebViewMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(MOVIE_ID_EXTRA, 0)

        binding.webContainer.apply {
            settings.javaScriptEnabled = true
            loadUrl(BuildConfig.WEBVIEW_URL + id)
        }
    }

    override fun onStop() {
        super.onStop()
        binding.webContainer.destroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webContainer.destroy()
    }

    companion object {
        const val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"
    }
}