package com.sabinhantu.baseapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestFood(

    @SerializedName("id")
    @Expose val id: Long,

    @SerializedName("quantity")
    @Expose val quantity: Int,

    @SerializedName("volunteerId")
    @Expose val volunteerId: Long,

    @SerializedName("address")
    @Expose val address: String,

    @SerializedName("description")
    @Expose val description: String,

    @SerializedName("date")
    @Expose val date: String,

    @SerializedName("longitude")
    @Expose val longitude: Double,

    @SerializedName("latitude")
    @Expose val latitude: Double,

    @SerializedName("expired")
    @Expose val expired: Boolean,

    @SerializedName("alreadyTaken")
    @Expose val alreadyTaken: Int
) {

    companion object{
        fun getPlaceholder(): RequestFood {
            return RequestFood(
                id = (10 until 20).random().toLong(),
                quantity = (10 until 20).random(),
                volunteerId = 1,
                address = "aaaaaaaa",
                description = "description ${(10 until 20).random()}",
                date = "01/01/1982",
                latitude = 0.toDouble(),
                longitude = 0.toDouble(),
                expired = false,
                alreadyTaken = (0 until 10).random()
            )
        }
    }
}