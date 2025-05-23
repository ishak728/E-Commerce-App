package com.example.e_commerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.AddressAdapter
import com.example.e_commerceapp.databinding.FragmentAddressesBinding
import com.example.e_commerceapp.model.UserAddress
import com.example.e_commerceapp.repository.address.AddressRepositoryImpl
import com.example.e_commerceapp.service.Retrofit
import com.example.e_commerceapp.util.makeToast
import com.example.e_commerceapp.viewmodel.AddressesViewModel
import com.example.e_commerceapp.viewmodel.createFactory


class AddressesFragment : Fragment() {
    private lateinit var binding:FragmentAddressesBinding
    private lateinit var viewModel:AddressesViewModel
    lateinit var addresses:List<UserAddress>
    lateinit var adapter: AddressAdapter
    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory= AddressesViewModel(AddressRepositoryImpl(Retrofit.api)).createFactory()

        viewModel=ViewModelProvider(this@AddressesFragment,factory)[AddressesViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAddressesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addAddress.setOnClickListener {
            val action=AddressesFragmentDirections.actionAddressesFragmentToAddAddressFragment()
            findNavController().navigate(action)
        }

        recyclerView=binding.rvAddresses
        recyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)


        getAddresses()
        println(1)

    }

    private fun getAddresses() {
        println(2)
        viewModel.getUserAddresses(530)

        viewModel.status.observe(viewLifecycleOwner){
            when(it){
                0->{
                    println("successful")
                }
                1->{
                    makeToast("Error",requireContext())
                }
            }
        }


        viewModel.address.observe(viewLifecycleOwner) { list ->
            addresses=list
            adapter=AddressAdapter(addresses)
            recyclerView.adapter=adapter

        }
    }


}