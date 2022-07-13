/*
 * Created by Muhammad Yashlan Iskandar on 7/1/22, 7:56 PM
 * Last modified 6/30/22, 9:30 PM
 */

package com.yashlan.androidexpertsub2.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yashlan.core.BuildConfig
import com.yashlan.core.customview.CustomWebView
import com.yashlan.core.utils.ScreenUtil

@SuppressLint("SetJavaScriptEnabled")
class DetailWebViewMovie : AppCompatActivity() {

    private lateinit var webContainer: CustomWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        ScreenUtil.forcePortraitScreenOrientation(this)
        ScreenUtil.setFullscreen(this)
        super.onCreate(savedInstanceState)

        webContainer = CustomWebView(this)
        val id = intent.getIntExtra(MOVIE_ID_EXTRA, 0)
        webContainer.apply {
            settings.javaScriptEnabled = true
            loadUrl(BuildConfig.WEBVIEW_URL + id)
        }
        setContentView(webContainer)
    }

    override fun onDestroy() {
        super.onDestroy()
        webContainer.apply {
            removeAllViews()
            removeAllViewsInLayout()
            destroy()
        }
    }

    companion object {
        const val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"
    }
}