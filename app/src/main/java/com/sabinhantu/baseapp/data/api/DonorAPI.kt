package com.sabinhantu.baseapp.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sabinhantu.baseapp.model.Donor
import com.sabinhantu.baseapp.model.Offer
import com.sabinhantu.baseapp.model.RequestFood
import retrofit2.Call
import retrofit2.http.*

interface DonorAPI {

    @POST("donor/offer")
    fun createOffer(@Body offer: OfferRequest): Call<Offer>

    @GET("donor/show-requests")
    fun getRequests(): Call<ArrayList<RequestFood>>

    @GET("donor/profile")
    fun getDonorProfile(
        @Query("donorId") donorId: String
    ): Call<Donor>

    @PUT("donor/provide")
    fun provide(@Body data: ProvideRequest): Call<ProvideResponse>
}

data class OfferRequest (

    @SerializedName("companyId")
    @Expose
    var companyId: Long,

    @SerializedName("date")
    @Expose
    var date: String,

    @SerializedName("quantity")
    @Expose
    var quantity: Int,

    @SerializedName("reserved")
    @Expose
    var reserved: Int,

    @SerializedName("inHouse")
    @Expose
    var inHouse: Boolean,

    @SerializedName("description")
    @Expose
    var description: String
)


data class ProvideResponse(
    @SerializedName("quantity")
    @Expose val quantity: Int,

    @SerializedName("reserved")
    @Expose val reservedQuantity: Int
)

data class ProvideRequest(
    @SerializedName("reserved")
    @Expose
    var reserved: Int,

    @SerializedName("requestId")
    @Expose
    var requestId: Long
)