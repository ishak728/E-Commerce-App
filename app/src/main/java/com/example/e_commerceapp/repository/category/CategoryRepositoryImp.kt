package com.example.e_commerceapp.repository.category

import com.example.e_commerceapp.model.CategoryResponse
import com.example.e_commerceapp.service.ApiService
import retrofit2.Call

class CategoryRepositoryImp(private val apiService:ApiService): CategoryRepository {
    override fun getCategories(): Call<CategoryResponse> {
         return apiService.getCategories()
    }
}