package com.example.e_commerceapp.repository.auth

import com.example.e_commerceapp.model.LoginRequest
import com.example.e_commerceapp.model.RegisterRequest
import com.example.e_commerceapp.model.RegisterResponse
import retrofit2.Call

interface AuthRepository {
    fun registerUser(registerRequest:RegisterRequest):Call<RegisterResponse>

    fun loginUser(loginRequest: LoginRequest):Call<RegisterResponse>

}