/*
 * Created by Muhammad Yashlan Iskandar on 7/8/22, 2:29 AM
 * Last modified 7/8/22, 2:29 AM
 */

package com.yashlan.core.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import java.lang.reflect.Field


class CustomWebView : WebView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        context.applicationContext,
        attrs
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        init()
    }

    override fun destroy() {
        super.destroy()
        try {
            if (sConfigCallback != null) sConfigCallback?.set(null, null)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun init() {
        webViewClient = MyWebViewClient()
    }

    private class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            try {
                view?.loadUrl(request?.url.toString())
            } catch (e: RuntimeException) {
                throw RuntimeException(e)
            }
            return true
        }
    }

    @SuppressLint("PrivateApi")
    companion object {
        private var sConfigCallback: Field? = null

        init {
            try {
                sConfigCallback = Class.forName("android.webkit.BrowserFrame")
                    .getDeclaredField("sConfigCallback")
                sConfigCallback?.isAccessible = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}