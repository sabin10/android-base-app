package com.sabinhantu.baseapp.data.api

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sabinhantu.baseapp.model.Donation
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AskerAPI {

    @POST("volunteer/create-request")
    fun createAskFood(@Body askFood: AskFoodRequest): Call<JsonObject>

    @GET("volunteer/show-offers")
    fun getDonations(): Call<ArrayList<Donation>>
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