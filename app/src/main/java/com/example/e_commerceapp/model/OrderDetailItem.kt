package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName

data class OrderDetailItem(
    @SerializedName("product_id") val productId: String,
    @SerializedName("quantity") val quantity: String,
    @SerializedName("unit_price") val unitPrice: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("product_name") val productName: String,
    @SerializedName("description") val description: String,
    @SerializedName("product_image_url") val productImageUrl: String
)
