package com.pixel.pizzayah

import android.app.Application
import com.pixel.pizzayah.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PizzaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PizzaApplication)
            modules(viewModelModule)
        }
    }
}