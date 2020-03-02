package br.com.drivmatics.axyz.remote

import br.com.drivmatics.axyz.utils.Constants.BASE_URL
import br.com.drivmatics.axyz.utils.isNetworkConnected
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiClient {
    private lateinit var retrofit: Retrofit
    private lateinit var apiServices: ApiServices

    fun getApiService(): ApiServices {
        getInstance()
        return apiServices
    }

    private fun getInstance(): Retrofit {
        if (this::retrofit.isInitialized.not()) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json").build()
                    chain.proceed(request)
                }
                .addInterceptor(interceptor)
                .addInterceptor(HeaderInterceptor())
                .addInterceptor(ConnectivityInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        }
        apiServices = retrofit.create(ApiServices::class.java)
        return retrofit
    }
}

class ConnectivityInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val isNetworkActive = isNetworkConnected()
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
        IOException("No network available, please check your WiFi or Data connection")
}
