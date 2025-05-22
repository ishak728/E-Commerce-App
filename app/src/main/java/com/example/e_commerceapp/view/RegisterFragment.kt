package com.example.e_commerceapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentRegisterBinding
import com.example.e_commerceapp.model.RegisterRequest
import com.example.e_commerceapp.model.RegisterResponse
import com.example.e_commerceapp.service.Retrofit
import com.example.e_commerceapp.util.makeToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val fullName = binding.etFullName.text.toString()
            val mobile = binding.etMobile.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            val request = RegisterRequest(fullName, mobile, email, password)

            if (request.isNotEmpty()){
                register(request)
            }else{
                makeToast( "Fields cannot be empty",requireContext())
            }


        }

        binding.tvGoToLogin.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    private fun register(request: RegisterRequest) {
        val call = Retrofit.api.registerUser(request)

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
               if (response.isSuccessful && response.body()!=null){
                   if (response.body()?.status==0 ){
                       response.body()?.message?.let { makeToast(it,requireContext()) }

                       val intent= Intent(requireContext(),HostActivity::class.java)
                       startActivity(intent)
                       requireActivity().finish()
                   }else{
                       response.body()?.message?.let { makeToast(it,requireContext()) }
                   }
               }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                println(t.localizedMessage)

            }
        })
    }



    fun RegisterRequest.isNotEmpty(): Boolean {
        with(this){
            if(fullName.isNotEmpty() && mobileNo.isNotEmpty() && password.isNotEmpty() && emailId.isNotEmpty() ){
                return true
            }else{
                return false
            }
        }
    }



}