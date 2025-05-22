package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName

data class SubCategory (
    @SerializedName("subcategory_id")
    val subCategoryId:String,

    @SerializedName("subcategory_name")
    val subCategoryName:String,

    @SerializedName("category_id")
    val categoryId:String,

    @SerializedName("subcategory_image_url")
    val subCategoryImageUrl:String,

    @SerializedName("is_active")
    val isActive:String
)


