package com.tarun.demo.remote

import com.tarun.demo.utils.Constants.BASE_URL
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
