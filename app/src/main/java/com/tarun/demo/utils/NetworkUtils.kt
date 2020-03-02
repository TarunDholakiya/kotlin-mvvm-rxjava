package com.tarun.demo.utils

import android.content.Context
import android.net.ConnectivityManager
import com.tarun.demo.MyApplication

object NetworkUtils {
    fun isNetworkConnected(): Boolean {
        return (MyApplication.get().getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager).activeNetworkInfo != null
    }
}