package com.tarun.demo.remote

import com.tarun.demo.MyApplication
import com.tarun.demo.utils.NetworkUtils
import com.tarun.demo.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val isNetworkActive = NetworkUtils.isNetworkConnected()
        return if (!isNetworkActive) {
            throw NoConnectivityException(
                message = String()
            )
        } else {
            chain.proceed(chain.request())
        }
    }

    /**
     * Throws NoConnectivityException if network not available
     */
    class NoConnectivityException(override var message: String) :
        IOException(MyApplication.get().getString(R.string.internet_not_available))
}