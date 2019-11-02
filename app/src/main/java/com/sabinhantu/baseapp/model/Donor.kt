package com.sabinhantu.baseapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Donor (
    @SerializedName("id")
    @Expose
    var id: Long,

    @SerializedName("CUI")
    @Expose
    val cui: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("password")
    @Expose
    val password: String,

    @SerializedName("phoneNumber")
    @Expose
    val phoneNumber: String,

    @SerializedName("latitude")
    @Expose
    var latitude: Long,

    @SerializedName("longitude")
    @Expose
    var longitude: Long,

    @SerializedName("type")
    @Expose
    var type: String,

    @SerializedName("address")
    @Expose
    var address: String,

    @SerializedName("picture")
    @Expose
    var picture: ByteArray
)