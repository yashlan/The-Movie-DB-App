/*
 * Created by Muhammad Yashlan Iskandar on 6/30/22, 10:00 PM
 * Last modified 6/30/22, 10:00 PM
 */

package com.yashlan.core.utils

import androidx.appcompat.app.AppCompatDelegate

enum class ThemeMode(val value: Int) {
    FOLLOW_SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    DARK_MODE_ON(AppCompatDelegate.MODE_NIGHT_YES),
    DARK_MODE_OFF(AppCompatDelegate.MODE_NIGHT_NO)
}