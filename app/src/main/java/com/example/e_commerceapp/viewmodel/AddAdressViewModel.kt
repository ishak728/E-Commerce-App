package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.model.AddAddressRequest
import com.example.e_commerceapp.model.RegisterResponse
import com.example.e_commerceapp.repository.address.AddressRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAdressViewModel(private val addressRepository: AddressRepository) : ViewModel() {
    private val _status = MutableLiveData<Int>()
    val status = _status

    fun addDeliveryAddress(addAddressRequest: AddAddressRequest) {
        val call=addressRepository.addDeliveryAddress(addAddressRequest)

        call.enqueue(object :Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful && response.body()!=null){
                   if ( response.body()!!.status==0){
                       _status.postValue(0)
                   }else{
                       _status.postValue(1)
                   }
                }
                else{
                    _status.postValue(1)

                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
               println(t.localizedMessage)
                _status.postValue(1)
            }
        })

    }
}