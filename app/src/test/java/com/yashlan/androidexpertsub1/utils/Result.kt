/*
 * Created by Muhammad Yashlan Iskandar on 7/12/22, 5:19 PM
 * Last modified 7/12/22, 5:19 PM
 */

package com.yashlan.androidexpertsub1.utils

sealed class Result<out R> private constructor() {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}