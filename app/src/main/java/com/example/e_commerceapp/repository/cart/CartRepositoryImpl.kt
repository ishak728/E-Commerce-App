package com.example.e_commerceapp.repository.cart

import com.example.e_commerceapp.model.OrderItem
import com.example.e_commerceapp.model.ProductDetailResponse
import com.example.e_commerceapp.service.ApiService
import com.example.e_commerceapp.service.dblocal.Dao
import retrofit2.Call

class CartRepositoryImpl(val apiService: ApiService,val dao: Dao):CartRepository {
    override fun getOrderItemsFromDao(): List<OrderItem> {
        return dao.getOrders()
    }

    override fun getProductDetail(productId: String): Call<ProductDetailResponse> {
        return apiService.getProductDetail(productId)
    }
}