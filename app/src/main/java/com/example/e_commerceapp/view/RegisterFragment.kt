package com.example.e_commerceapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentRegisterBinding
import com.example.e_commerceapp.model.RegisterRequest
import com.example.e_commerceapp.model.RegisterResponse
import com.example.e_commerceapp.repository.auth.AuthRepositoryImpl
import com.example.e_commerceapp.service.Retrofit
import com.example.e_commerceapp.util.makeToast
import com.example.e_commerceapp.viewmodel.LoginFragmentViewModel
import com.example.e_commerceapp.viewmodel.RegisterFragmentViewModel
import com.example.e_commerceapp.viewmodel.createFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var viewModel:RegisterFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = RegisterFragmentViewModel(
            AuthRepositoryImpl(Retrofit.api)
        ).createFactory()

        viewModel = ViewModelProvider(this, factory)[RegisterFragmentViewModel::class.java]



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

        viewModel.registerUser(request)

        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                0 -> {

                    val intent= Intent(requireContext(),HostActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                1 -> {
                    makeToast("failed",requireContext())
                }
            }
        }
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