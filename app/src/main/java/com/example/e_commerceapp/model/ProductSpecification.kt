package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName

data class ProductSpecification(
    @SerializedName("specification_id")
    val specificationId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("specification")
    val specification: String,

    @SerializedName("display_order")
    val displayOrder: String
)

