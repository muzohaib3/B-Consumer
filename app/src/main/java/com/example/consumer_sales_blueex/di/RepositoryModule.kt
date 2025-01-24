package com.example.consumer_sales_blueex.di

import com.example.consumer_sales_blueex.datasource.repo.GeneralRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single {
        GeneralRepository()
    }

}