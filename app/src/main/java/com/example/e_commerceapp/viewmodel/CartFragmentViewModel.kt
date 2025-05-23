package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.model.OrderItem
import com.example.e_commerceapp.model.ProductDetailResponse
import com.example.e_commerceapp.repository.cart.CartRepository
import com.example.e_commerceapp.service.dblocal.Dao
import retrofit2.Call
import retrofit2.Callback

class CartFragmentViewModel(val cartRepository: CartRepository) : ViewModel() {

    private val _products = MutableLiveData<List<ProductDetailResponse>>()
    val products=_products


    fun getProductDetailsFromOrderItems() {
        val orderItems = cartRepository.getOrderItemsFromDao()

        val resultList = mutableListOf<ProductDetailResponse>()
        var completedCount = 0
        val totalCount = orderItems.size

        if (totalCount == 0) {
            _products.postValue(emptyList())
            return
        }

        orderItems.forEach { orderItem ->
            cartRepository.getProductDetail(orderItem.productId.toString())
                .enqueue(object : Callback<ProductDetailResponse> {
                    override fun onResponse(
                        call: Call<ProductDetailResponse>,
                        response: retrofit2.Response<ProductDetailResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            resultList.add(response.body()!!)
                        }
                        completedCount++
                        if (completedCount == totalCount) {
                            _products.postValue(resultList)
                        }
                    }

                    override fun onFailure(call: Call<ProductDetailResponse>, t: Throwable) {
                        completedCount++
                        if (completedCount == totalCount) {
                            _products.postValue(resultList)
                        }
                    }
                })
        }
    }

}