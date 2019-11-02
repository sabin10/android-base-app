package com.sabinhantu.baseapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Donor (
    @SerializedName("id")
    @Expose
    var id: Long? = null,

    @SerializedName("CUI")
    @Expose
    val cui: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("email")
    @Expose
    val email: String? = null,

    @SerializedName("password")
    @Expose
    val password: String? = null,

    @SerializedName("phoneNumber")
    @Expose
    val phoneNumber: String? = null,

    @SerializedName("latitude")
    @Expose
    var latitude: Long? = null,

    @SerializedName("longitude")
    @Expose
    var longitude: Long? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("address")
    @Expose
    var address: String? = null,

    @SerializedName("picture")
    @Expose
    var picture: ByteArray? = null
)