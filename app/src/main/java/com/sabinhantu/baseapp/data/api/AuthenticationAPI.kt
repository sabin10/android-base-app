package com.sabinhantu.baseapp.data.api

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @POST("donor/register")
    fun registerAsDonor(@Body data: RegisterDonorApi): Call<JsonObject>

    @POST("volunteer/register")
    fun registerAsVolunteer(@Body data: RegisterVolunteerApi): Call<JsonObject>


}

data class RegisterDonorApi(
    @SerializedName("name")
    @Expose val name: String,

    @SerializedName("email")
    @Expose val email: String,

    @SerializedName("password")
    @Expose val password: String,

    @SerializedName("phoneNumber")
    @Expose val phoneNumber: String,

    @SerializedName("latitudeX")
    @Expose val latitudeX: Double? = null,

    @SerializedName("latitudeY")
    @Expose val latitudeY: Double? = null,

    @SerializedName("type")
    @Expose val type: String,

    @SerializedName("picture")
    @Expose val picture: String? = null,

    @SerializedName("cui")
    @Expose val cui: String

)

data class RegisterVolunteerApi(
    @SerializedName("name")
    @Expose val name: String,

    @SerializedName("email")
    @Expose val email: String,

    @SerializedName("password")
    @Expose val password: String,

    @SerializedName("phoneNumber")
    @Expose val phoneNumber: String,

    @SerializedName("type")
    @Expose val type: String,

    @SerializedName("picture")
    @Expose val picture: String? = null
)
