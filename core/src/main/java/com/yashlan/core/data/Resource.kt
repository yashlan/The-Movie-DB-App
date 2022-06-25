/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 9:54 PM
 * Last modified 6/25/22, 9:54 PM
 */

package com.yashlan.core.data

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}