/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 1:51 AM
 * Last modified 6/26/22, 1:51 AM
 */

package com.yashlan.core.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes resId: Int) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}