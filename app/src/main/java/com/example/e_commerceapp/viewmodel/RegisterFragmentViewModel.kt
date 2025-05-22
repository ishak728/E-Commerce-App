package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.model.LoginRequest
import com.example.e_commerceapp.model.RegisterRequest
import com.example.e_commerceapp.model.RegisterResponse
import com.example.e_commerceapp.repository.auth.AuthRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragmentViewModel (
    private val authRepository: AuthRepository
): ViewModel() {
    private val _status= MutableLiveData<Int>()
    val status=_status




    fun registerUser(registerRequest: RegisterRequest){
        val call=authRepository.registerUser(registerRequest)

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful && response.body()!=null){
                    if (response.body()?.status==0 ){
                      _status.postValue(0)


                    }else{
                        _status.postValue(1)
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                println(t.localizedMessage)
                _status.postValue(1)
            }
        })
    }




}