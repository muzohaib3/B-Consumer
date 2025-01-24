package com.example.consumer_sales_blueex.di

import com.example.consumer_sales_blueex.network.ApiService
import com.example.consumer_sales_blueex.network.Routes.BASEURL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

val NetworkModule = module {
    single { getGsonConverterFactory() }
    single { provideOkHttpClient() }
    single { getRetrofitInstance(get(), get()) }
    single { provideAPIClient(get()) }
}

fun getRetrofitInstance(gsonConverterFactory: GsonConverterFactory, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASEURL)
        .client(client)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
        override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
        override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
    })

    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, java.security.SecureRandom())

    val sslSocketFactory = sslContext.socketFactory

    val client = OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }

    client.addInterceptor { chain: Interceptor.Chain ->
        val newRequest = chain.request().newBuilder()
            //.addHeader("Authorization", "Bearer ".plus(AppPreferences.loginData?.token))
            .build()
        chain.proceed(newRequest)
    }

    return client.build()
}

fun provideAPIClient(retrofit: Retrofit): ApiService {
    return try {
        retrofit.create(ApiService::class.java)
    } catch (e: Exception) {
        println("provideAPIClient >> ${e.message}")
        throw e
    }
}

fun getGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}
