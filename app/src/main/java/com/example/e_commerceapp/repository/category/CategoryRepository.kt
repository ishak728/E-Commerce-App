package com.example.e_commerceapp.repository.category

import com.example.e_commerceapp.model.CategoryResponse
import retrofit2.Call

interface CategoryRepository {
    fun getCategories(): Call<CategoryResponse>
}