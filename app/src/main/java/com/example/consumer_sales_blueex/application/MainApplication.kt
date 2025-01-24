package com.example.consumer_sales_blueex.application

import android.app.Application
import com.example.consumer_sales_blueex.di.NetworkModule
import com.example.consumer_sales_blueex.di.RepositoryModule
//import com.example.consumer_sales_blueex.di.RoomModule
import com.example.consumer_sales_blueex.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{

            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    NetworkModule,
                    RepositoryModule,
                    ViewModelModule
                )
            )

        }
    }
}