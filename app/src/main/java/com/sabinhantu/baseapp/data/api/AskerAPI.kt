package com.sabinhantu.baseapp.data.api

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sabinhantu.baseapp.model.Donation
import com.sabinhantu.baseapp.model.Donor
import com.sabinhantu.baseapp.model.Volunteer
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AskerAPI {

    @POST("volunteer/create-request")
    fun createAskFood(@Body askFood: AskFoodRequest): Call<JsonObject>

    @GET("volunteer/show-offers")
    fun getDonations(): Call<ArrayList<Donation>>

    @GET("/volunteer/profile")
    fun getVolunteerProfile(
        @Query("volunteerId") volunteerId: String
    ): Call<Volunteer>

    @POST("volunteer/raise")
    fun createAsk(@Body data: AskRequest): Call<AskResponse>
}

data class AskFoodRequest(
    @SerializedName("quantity")
    @Expose
    var quantity: Int,

    @SerializedName("volunteerId")
    @Expose
    var volunteerId: Long,

    @SerializedName("address")
    @Expose
    var address: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("date")
    @Expose
    var date: String? = null,

    @SerializedName("expired")
    @Expose
    var expired: Boolean = false,

    @SerializedName("alreadyTaken")
    @Expose
    var alreadyTaken: Int = 0
)

data class AskRequest(
    @SerializedName("reservedQuantity")
    @Expose
    var reservedQuantity: Int,

    @SerializedName("volunteerId")
    @Expose
    var volunteerId: Long,

    @SerializedName("offerId")
    @Expose
    var companyId: Long
)

data class AskResponse(
    @SerializedName("quantity")
    @Expose val quantity: Int,

    @SerializedName("reserved")
    @Expose val reservedQuantity: Int
)