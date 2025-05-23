package com.example.e_commerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentAddAddressBinding
import com.example.e_commerceapp.databinding.FragmentAddressesBinding
import com.example.e_commerceapp.model.AddAddressRequest
import com.example.e_commerceapp.repository.address.AddressRepositoryImpl
import com.example.e_commerceapp.repository.category.CategoryRepositoryImp
import com.example.e_commerceapp.service.Retrofit
import com.example.e_commerceapp.util.makeToast
import com.example.e_commerceapp.viewmodel.AddAdressViewModel
import com.example.e_commerceapp.viewmodel.DashboardFragmentViewModel
import com.example.e_commerceapp.viewmodel.createFactory
import com.google.gson.annotations.SerializedName


class AddAddressFragment : Fragment() {

    private lateinit var binding:FragmentAddAddressBinding
    private lateinit var viewModel:AddAdressViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory=AddAdressViewModel(AddressRepositoryImpl(Retrofit.api)).createFactory()

        viewModel=ViewModelProvider(this@AddAddressFragment,factory)[AddAdressViewModel::class.java]




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentAddAddressBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnAddAddress.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val address = binding.etAddress.text.toString()
            val request = AddAddressRequest(530,title,address )
            addDeliveryAddress(request)
        }



    }


    fun addDeliveryAddress(request: AddAddressRequest){
        viewModel.addDeliveryAddress(request)

        viewModel.status.observe(viewLifecycleOwner){
            when(it){
                0->{
                    makeToast("Address added",requireContext())
                    findNavController().popBackStack()
                }
                1->{
                    makeToast("Try Again !",requireContext())
                }
            }
        }
    }


}

