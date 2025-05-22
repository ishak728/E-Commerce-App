package com.example.e_commerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceapp.model.Category
import com.example.e_commerceapp.model.CategoryResponse
import com.example.e_commerceapp.repository.category.CategoryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragmentViewModel(private val categoryRepository: CategoryRepository):ViewModel() {

    init {
        getCategoriesData()
    }
    private val _categories= MutableLiveData<List<Category>>()
    val categories=_categories

    private val _error= MutableLiveData<List<Category>>()
    val error=_error

    fun getCategoriesData() {
        val call = categoryRepository.getCategories()
        call.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body().let {
                       _categories.postValue(it!!.categories)
                    }
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {

            }
        })
    }
}