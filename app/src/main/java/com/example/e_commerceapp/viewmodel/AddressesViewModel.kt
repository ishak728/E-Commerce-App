package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.model.UserAddress
import com.example.e_commerceapp.model.UserAddressResponse
import com.example.e_commerceapp.repository.address.AddressRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressesViewModel(private val addressRepository: AddressRepository) : ViewModel() {

    private val _status = MutableLiveData<Int>()
    val status = _status

    private val _addresses = MutableLiveData<List<UserAddress>>()
    val address = _addresses


    fun getUserAddresses(userId: Int) {
        val call = addressRepository.getUserAddresses(userId)

        call.enqueue(object : Callback<UserAddressResponse> {
            override fun onResponse(
                call: Call<UserAddressResponse>,
                response: Response<UserAddressResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    if (response.body()!!.status==0){
                        _status.postValue(0)
                        _addresses.postValue(response.body()!!.addresses)
                    }else{
                        _status.postValue(1)
                    }
                } else {
                    _status.postValue(1)
                }
            }

            override fun onFailure(call: Call<UserAddressResponse>, t: Throwable) {
                println(t.localizedMessage)
                _status.postValue(1)
            }
        })
    }
}