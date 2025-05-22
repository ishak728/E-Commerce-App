package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName


data class OrderDetailResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("order") val order: OrderDetail?
)
