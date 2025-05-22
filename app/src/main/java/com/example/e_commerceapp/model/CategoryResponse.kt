package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse (
    @SerializedName("status")
    val status:String,

    @SerializedName("message")
    val message:String,

    @SerializedName("categories")
    val categories:List<Category>
)

