/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 2:02 PM
 * Last modified 6/26/22, 2:02 PM
 */

package com.yashlan.core.utils

import android.content.pm.ActivityInfo
import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

object ScreenUtil {
    fun setFullscreen(app: AppCompatActivity) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
                app.window.attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                app.window.setDecorFitsSystemWindows(false)
                app.window.insetsController?.apply {
                    hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                    systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            }
            else -> {
                app.window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )
            }
        }
        app.supportActionBar?.hide()
    }

    fun forcePortraitScreenOrientation(app: AppCompatActivity) {
        app.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
    }
}
