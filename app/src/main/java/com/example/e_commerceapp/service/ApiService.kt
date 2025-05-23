package com.example.e_commerceapp.service

import com.example.e_commerceapp.model.AddAddressRequest
import com.example.e_commerceapp.model.CategoryResponse
import com.example.e_commerceapp.model.LoginRequest
import com.example.e_commerceapp.model.PlaceOrderRequest
import com.example.e_commerceapp.model.PlaceOrderResponse
import com.example.e_commerceapp.model.ProductDetailResponse
import com.example.e_commerceapp.model.ProductResponse
import com.example.e_commerceapp.model.RegisterRequest
import com.example.e_commerceapp.model.RegisterResponse
import com.example.e_commerceapp.model.SearchResponse
import com.example.e_commerceapp.model.SubCategoryResponse
import com.example.e_commerceapp.model.UserAddressResponse
import com.example.e_commerceapp.model.UserOrdersResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("user/register")
    fun registerUser(
        @Body RegisterRequest:RegisterRequest
    ):Call<RegisterResponse>

    @POST("user/auth")
    fun loginUser(
        @Body loginRequest: LoginRequest
    ):Call<RegisterResponse>

    @GET("Category")
    fun getCategories():Call<CategoryResponse>


    //Search Product

    @GET("Product/search")
    fun searchProduct(
        @Query("query") query:String
    ):Call<SearchResponse>


    //Get Sub Categories of Some Category
    @GET("SubCategory")
    fun getSubCategory(
        @Query("category_id") categoryId:String
    ):Call<SubCategoryResponse>

    //Get list of products by sub category
    @GET("SubCategory/products/{sub_category_id}")
    fun getSubCategoryProducts(
        @Path("sub_category_id") subCategoryId:String
    ):Call<ProductResponse>



    //
    @GET("Product/details/{product_id}")
    fun getProductDetail(
        @Path("product_id") productId:String
    ):Call<ProductDetailResponse>

    @POST("User/address")
    fun addDeliveryAddress(
        @Body addAddressRequest: AddAddressRequest
    ):Call<RegisterResponse>

    @GET("User/addresses/{user_id}")
    fun getUserAddresses(
        @Path("user_id") userId:Int
    ):Call<UserAddressResponse>

    @POST("Order")
    fun placeOrder(
        @Body placeOrderRequest: PlaceOrderRequest
    ):Call<PlaceOrderResponse>


    @GET(" Order/userOrders/{user_id}")
    fun getAllUserOrders(
        @Path("user_id") userId:Int
    ):Call<UserOrdersResponse>


    @GET("Order")
    fun getOrderDetails(
        @Query("order_id") orderId:String
    )



}