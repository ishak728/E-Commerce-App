package com.example.e_commerceapp.repository.address

import com.example.e_commerceapp.model.AddAddressRequest
import com.example.e_commerceapp.model.RegisterResponse
import com.example.e_commerceapp.model.UserAddressResponse
import retrofit2.Call

interface AddressRepository {

    fun addDeliveryAddress(
       addAddressRequest: AddAddressRequest
    ): Call<RegisterResponse>

    fun getUserAddresses(
        userId:Int
    ):Call<UserAddressResponse>
}