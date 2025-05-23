package com.example.e_commerceapp.repository.cart

import com.example.e_commerceapp.model.OrderItem
import com.example.e_commerceapp.model.ProductDetailResponse
import retrofit2.Call
import retrofit2.http.Path

interface CartRepository {

    fun getOrderItemsFromDao():List<OrderItem>

    fun getProductDetail(
       productId:String
    ): Call<ProductDetailResponse>
}