package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName

data class ProductImage(
    @SerializedName("image")
    val image: String,

    @SerializedName("display_order")
    val displayOrder: String
)

