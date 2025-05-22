package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName

data class Category (

    @SerializedName("category_id")
    val categoryId:String,

    @SerializedName("category_name")
    val categoryName:String,

    @SerializedName("category_image_url")
    val categoryImage:String,

    @SerializedName("is_active")
    val isActive:String
)

