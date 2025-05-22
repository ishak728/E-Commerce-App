package com.example.e_commerceapp.model



import com.google.gson.annotations.SerializedName

data class PlaceOrderResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("order_id")
    val orderId: Int? = null
)
