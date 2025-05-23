package com.example.e_commerceapp.repository.address

import com.example.e_commerceapp.model.AddAddressRequest
import com.example.e_commerceapp.model.RegisterResponse
import com.example.e_commerceapp.model.UserAddressResponse
import com.example.e_commerceapp.service.ApiService
import retrofit2.Call

class AddressRepositoryImpl(private val apiService: ApiService):AddressRepository {
    override fun addDeliveryAddress(addAddressRequest: AddAddressRequest): Call<RegisterResponse> {
        return apiService.addDeliveryAddress(addAddressRequest)
    }

    override fun getUserAddresses(userId: Int): Call<UserAddressResponse> {
        return apiService.getUserAddresses(userId)
    }
}