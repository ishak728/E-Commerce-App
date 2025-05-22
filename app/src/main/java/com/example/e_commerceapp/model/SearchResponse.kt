package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("products")
    val products: List<Product>
)

