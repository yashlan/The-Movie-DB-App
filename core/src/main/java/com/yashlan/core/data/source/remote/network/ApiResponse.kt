/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 10:26 PM
 * Last modified 6/25/22, 10:26 PM
 */

package com.yashlan.core.data.source.remote.network

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}