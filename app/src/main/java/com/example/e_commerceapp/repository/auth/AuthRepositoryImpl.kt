package com.example.e_commerceapp.repository.auth

import com.example.e_commerceapp.model.LoginRequest
import com.example.e_commerceapp.model.RegisterRequest
import com.example.e_commerceapp.model.RegisterResponse
import com.example.e_commerceapp.service.ApiService
import retrofit2.Call

class AuthRepositoryImpl(private val apiService: ApiService):AuthRepository {
    override fun registerUser(registerRequest: RegisterRequest): Call<RegisterResponse> {
        return apiService.registerUser(registerRequest)
    }

    override fun loginUser(loginRequest: LoginRequest): Call<RegisterResponse> {
        return apiService.loginUser(loginRequest)
    }
}