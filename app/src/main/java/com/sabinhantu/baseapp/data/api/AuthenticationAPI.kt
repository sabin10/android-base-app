package com.sabinhantu.baseapp.data.api

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthenticationAPI {

    @GET("donor/login")
    fun loginAsDonor(
        @Query("email") email: String,
        @Query("password")password: String
    ): Call<JsonObject>

    @GET("volunteer/login")
    fun loginAsVolunteer(
        @Query("email") email: String,
        @Query("password")password: String
    ): Call<JsonObject>



}