package br.com.drivmatics.axyz

import androidx.multidex.MultiDexApplication
import com.benefleet.sdk.axys.features.sensor.AxysSdk


class App : MultiDexApplication() {

    companion object {
        private lateinit var instance: App
        @JvmStatic
        fun get() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AxysSdk.create(this)
    }
}