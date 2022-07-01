/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 4:21 PM
 * Last modified 6/25/22, 4:21 PM
 */

package com.yashlan.androidexpertsub1.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.yashlan.androidexpertsub1.R
import com.yashlan.androidexpertsub1.home.HomeActivity
import com.yashlan.core.utils.ThemeUtils
import com.yashlan.core.utils.forcePortraitScreenOrientation
import com.yashlan.core.utils.setFullscreen

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        forcePortraitScreenOrientation()
        setFullscreen()
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