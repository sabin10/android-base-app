package com.sabinhantu.baseapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Donation(

    @SerializedName("id")
    @Expose val id: Long? = null,

    @SerializedName("quantity")
    @Expose val quantity: Int? = null,

    @SerializedName("companyId")
    @Expose val companyId: Long? = null,

    @SerializedName("address")
    @Expose val address: String? = null,

    @SerializedName("description")
    @Expose val description: String? = null,

    @SerializedName("date")
    @Expose val date: String? = null,

    @SerializedName("expired")
    @Expose val expired: Boolean? = null,

    @SerializedName("inHouse")
    @Expose val inHouse: Boolean? = null,

    @SerializedName("reserved")
    @Expose val alreadyTaken: Int? = null
) {

    companion object{
        fun getPlaceholder(): Donation {
            return Donation(
                id = (10 until 20).random().toLong(),
                quantity = (10 until 20).random(),
                companyId = 10,
                address = "aaaaaaaa",
                description = "description ${(10 until 20).random()}",
                date = "01/01/1982",
                expired = false,
                alreadyTaken = (0 until 10).random()
            )
        }
    }
}