/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 9:34 PM
 * Last modified 6/26/22, 9:34 PM
 */

package com.yashlan.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.yashlan.core.BuildConfig
import com.yashlan.core.utils.forcePortraitScreenOrientation
import com.yashlan.core.utils.setFullscreen
import com.yashlan.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class WebViewFavoriteMovie : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        forcePortraitScreenOrientation()
        setFullscreen()
        super.onCreate(savedInstanceState)

        loadKoinModules(favoriteModule)

        val myWebView = WebView(applicationContext)
        myWebView.settings.javaScriptEnabled = true
        setContentView(myWebView)
        val id = intent.getIntExtra(MOVIE_ID_EXTRA, 0)
        myWebView.loadUrl(BuildConfig.WEBVIEW_URL + id)
    }

    companion object {
        private const val MOVIE_ID_EXTRA = "MOVIE_ID_EXTRA"
    }
}