package com.example.e_commerceapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.example.e_commerceapp.model.LoginRequest
import com.example.e_commerceapp.model.RegisterRequest
import com.example.e_commerceapp.model.RegisterResponse
import com.example.e_commerceapp.service.Retrofit
import com.example.e_commerceapp.util.makeToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvGoToRegister.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, RegisterFragment())
                .addToBackStack("Login")
                .commit()
        }

        binding.btnLogin.setOnClickListener {

            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()

            val loginRequest=LoginRequest(email,password)

            if (loginRequest.isNotEmpty()){
                login(loginRequest)
            }else{
                makeToast( "Fields cannot be empty",requireContext())
            }


        }

    }

    private fun login(loginRequest: LoginRequest) {
        val call=Retrofit.api.loginUser(loginRequest)

        call.enqueue(object:Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful && response.body()!=null){
                    if (response.body()?.status==0 ){
                        response.body()?.message?.let { makeToast(it,requireContext()) }

                        val intent=Intent(requireContext(),HostActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()

                    }else{
                        response.body()?.message?.let { makeToast(it,requireContext()) }
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
               makeToast("Failure",requireContext())
            }
        })
    }

    fun LoginRequest.isNotEmpty(): Boolean {
        with(this){
            if( password.isNotEmpty() && emailId.isNotEmpty() ){
                return true
            }else{
                return false
            }
        }
    }


}


