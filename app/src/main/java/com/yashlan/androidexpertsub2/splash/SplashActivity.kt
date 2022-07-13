/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 4:21 PM
 * Last modified 6/25/22, 4:21 PM
 */

package com.yashlan.androidexpertsub2.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.yashlan.androidexpertsub2.R
import com.yashlan.androidexpertsub2.home.HomeActivity
import com.yashlan.core.utils.ScreenUtil
import com.yashlan.core.utils.ThemeUtils

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ScreenUtil.forcePortraitScreenOrientation(this)
        ScreenUtil.setFullscreen(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ThemeUtils(this).getTheme()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, DELAY)
    }

    companion object {
        private const val DELAY: Long = 1000
    }
}