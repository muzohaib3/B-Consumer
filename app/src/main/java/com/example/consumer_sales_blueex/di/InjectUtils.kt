package com.example.consumer_sales_blueex.di

import android.app.Application
import com.example.consumer_sales_blueex.datasource.repo.GeneralRepository
import com.example.consumer_sales_blueex.datasource.viewmodel.MainViewModel
import com.example.consumer_sales_blueex.network.ApiService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object InjectUtils : KoinComponent {
    /**
     * Get Single instance of Application Context
     */
    val appContext: Application by inject()

    /**
     * Get Single instance of Retrofit WEB API Service
     */
    val apiService: ApiService by inject()

    /**
     * Get Single instance of GeneralRepository
     */
    val repository : GeneralRepository by inject()


    val viewModel : MainViewModel by inject()

}