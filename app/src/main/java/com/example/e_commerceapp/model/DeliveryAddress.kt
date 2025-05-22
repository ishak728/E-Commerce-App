package com.example.e_commerceapp.model



import com.google.gson.annotations.SerializedName

data class DeliveryAddress(
    @SerializedName("title")
    val title: String,

    @SerializedName("address")
    val address: String
)
