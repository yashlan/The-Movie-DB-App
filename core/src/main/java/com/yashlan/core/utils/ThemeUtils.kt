/*
 * Created by Muhammad Yashlan Iskandar on 6/30/22, 10:07 PM
 * Last modified 6/30/22, 10:07 PM
 */

package com.yashlan.core.utils

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class ThemeUtils(activity: Activity) {

    private val preference = activity.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val _activity = activity

    fun updateTheme(mode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(mode)
        preference.edit().putInt(THEME_MODE, mode).apply()
        _activity.recreate()
        return true
    }

    fun getTheme() {
        when(preference.getInt(THEME_MODE, 0)) {
            ThemeMode.FOLLOW_SYSTEM.value -> {
                updateTheme(ThemeMode.FOLLOW_SYSTEM.value)
            }
            ThemeMode.DARK_MODE_ON.value -> {
                updateTheme(ThemeMode.DARK_MODE_ON.value)
            }

            ThemeMode.DARK_MODE_OFF.value -> {
                updateTheme(ThemeMode.DARK_MODE_OFF.value)
            }
        }
    }

    companion object {
        private const val PREF_NAME = "THEME_PREF"
        private const val THEME_MODE = "THEME_MODE"
    }
}