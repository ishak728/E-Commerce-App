package com.example.e_commerceapp.model



import com.google.gson.annotations.SerializedName

data class PlaceOrderRequest(
    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("delivery_address")
    val deliveryAddress: DeliveryAddress,

    @SerializedName("items")
    val items: List<OrderItem>,

    @SerializedName("bill_amount")
    val billAmount: Int,

    @SerializedName("payment_method")
    val paymentMethod: String
)
