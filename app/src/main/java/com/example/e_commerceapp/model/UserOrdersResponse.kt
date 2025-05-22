package com.example.e_commerceapp.model



import com.google.gson.annotations.SerializedName

data class UserOrdersResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("orders")
    val orders: List<UserOrder>
)
