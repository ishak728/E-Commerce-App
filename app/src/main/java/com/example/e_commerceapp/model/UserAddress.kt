package com.example.e_commerceapp.model



import com.google.gson.annotations.SerializedName

data class UserAddress(
    @SerializedName("address_id")
    val addressId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("address")
    val address: String
)
