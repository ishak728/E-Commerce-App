package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email_id")
    val emailId:String,

    @SerializedName("password")
    val password:String
)
