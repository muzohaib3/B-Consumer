package com.example.consumer_sales_blueex.di

import com.example.consumer_sales_blueex.datasource.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel {
        MainViewModel()
    }

}