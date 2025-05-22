package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName

data class SubCategoryResponse(
    @SerializedName("status")
    val status:String,

    @SerializedName("message")
    val message:String,

    @SerializedName("subcategories")
    val subCategory:List<SubCategory>

)


