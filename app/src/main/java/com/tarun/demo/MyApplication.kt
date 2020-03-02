package com.tarun.demo

import android.app.Application

class MyApplication : Application() {

    companion object {
        private lateinit var instance: MyApplication
        @JvmStatic
        fun get() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}