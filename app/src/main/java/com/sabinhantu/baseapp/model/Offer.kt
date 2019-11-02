package com.sabinhantu.baseapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Offer (
    @SerializedName("id")
    @Expose
    var id: Long = 0,

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
    var description: String,

    @SerializedName("expired")
    @Expose
    var expired: Boolean
)