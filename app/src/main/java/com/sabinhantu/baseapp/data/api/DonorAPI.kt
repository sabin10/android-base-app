package com.sabinhantu.baseapp.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sabinhantu.baseapp.model.Offer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DonorAPI {

    @POST("donor/offer")
    fun createOffer(@Body offer: OfferRequest): Call<Offer>
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