package com.example.e_commerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.ProductAdapter
import com.example.e_commerceapp.databinding.FragmentProductBinding
import com.example.e_commerceapp.model.Product
import com.example.e_commerceapp.model.ProductResponse
import com.example.e_commerceapp.service.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private lateinit var adapter: ProductAdapter
    private var allProducts: List<Product> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProducts.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        val subCategoryId=arguments?.getString("subCategoryId")
        subCategoryId?.let {
            getSubCategoryProducts(it)
        }

    }


    fun getSubCategoryProducts(subCategoryId:String){

        val call=Retrofit.api.getSubCategoryProducts(subCategoryId)

        call.enqueue(object :Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                     allProducts = response.body()!!.products
                    adapter=ProductAdapter(allProducts)
                    binding.rvProducts.adapter=adapter




                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                println(t.localizedMessage)
            }
        })
    }


}