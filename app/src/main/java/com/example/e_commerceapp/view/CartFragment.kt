package com.example.e_commerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.CartAdapter
import com.example.e_commerceapp.databinding.FragmentCartBinding
import com.example.e_commerceapp.model.OrderItem
import com.example.e_commerceapp.model.ProductDetail
import com.example.e_commerceapp.model.ProductDetailResponse
import com.example.e_commerceapp.repository.cart.CartRepositoryImpl
import com.example.e_commerceapp.service.Retrofit
import com.example.e_commerceapp.service.dblocal.Dao
import com.example.e_commerceapp.service.local.AppDatabase
import com.example.e_commerceapp.viewmodel.CartFragmentViewModel
import com.example.e_commerceapp.viewmodel.createFactory


class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    lateinit var viewModel: CartFragmentViewModel
    lateinit var products:List<ProductDetail>
    lateinit var recyclerView:RecyclerView
    lateinit var adapter: CartAdapter

    lateinit var orderItems: List<OrderItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = CartFragmentViewModel(
            CartRepositoryImpl(
                Retrofit.api,
                AppDatabase.getAppDatabase(requireContext()).getDao()
            )
        ).createFactory()

        viewModel = ViewModelProvider(this, factory)[CartFragmentViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=binding.rvCartFragment
        recyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        getProductInCart()

    }


    fun getProductInCart() {
        viewModel.getProductDetailsFromOrderItems()

        viewModel.products.observe(viewLifecycleOwner){
            products=it.map { productDetailResponse->
                productDetailResponse.product

            }
            adapter=CartAdapter(products)
            recyclerView.adapter = adapter

        }

    }


}