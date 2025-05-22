package com.example.e_commerceapp.model


import com.google.gson.annotations.SerializedName

data class UserAddressResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("addresses")
    val addresses: List<UserAddress>
)
