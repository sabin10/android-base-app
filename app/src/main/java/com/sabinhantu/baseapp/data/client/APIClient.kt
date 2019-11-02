package com.sabinhantu.baseapp.data.client

import com.sabinhantu.SABApplication
import com.sabinhantu.baseapp.data.interceptors.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object APIClient {

    val BASE_URL = "http://172.16.77.58/"

    val networkInterceptor by lazy {
        NetworkInterceptor(context = SABApplication.instance)
    }

    val loggingInterceptor: HttpLoggingInterceptor by lazy {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    val retrofitClient: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}