package com.sabinhantu.baseapp.data.client

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//object AuthSessionAPIClient: APIClient() {
//
//    private val authInterceptor by lazy {
//        ECSessionPreAuthInterceptor(context = ECApplicationController.instance)
//    }
//
//    val retrofitClient: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(getBaseApi())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//    }
//
//    private val okHttpClient: OkHttpClient by lazy {
//        OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
//            .addInterceptor(networkInterceptor)
//            .addInterceptor(loggingInterceptor)
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .build()
//    }
//
//}