package com.example.e_commerceapp.model



import com.google.gson.annotations.SerializedName

data class AddAddressRequest(
    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("address")
    val address: String
)
