package com.sabinhantu.baseapp.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sabinhantu.baseapp.model.Offer
import com.sabinhantu.baseapp.model.RequestFood
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DonorAPI {

    @POST("donor/offer")
    fun createOffer(@Body offer: OfferRequest): Call<Offer>

    @GET("donor/show-requests")
    fun getRequests(): Call<ArrayList<RequestFood>>
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