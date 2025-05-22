package com.example.e_commerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.adapter.CategoryAdapter
import com.example.e_commerceapp.databinding.FragmentDashboardBinding
import com.example.e_commerceapp.model.Category
import com.example.e_commerceapp.model.CategoryResponse
import com.example.e_commerceapp.service.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardFragment : Fragment() {


    lateinit var binding: FragmentDashboardBinding
    lateinit var categories: List<Category>
    lateinit var adapter: CategoryAdapter
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        getCategories()

        recyclerView = binding.rvCategory
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)


    }


    fun getCategories() {
        val call = Retrofit.api.getCategories()
        call.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body().let {
                        categories = it!!.categories
                        adapter = CategoryAdapter(categories)
                        recyclerView.adapter = adapter
                        initializeCategoryOnClickListener()
                    }
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                println(t.localizedMessage)
            }
        })
    }

    private fun initializeCategoryOnClickListener() {

        adapter.ItemClickListener { position ->

            println("${categories[position].categoryName} clicked !!!")

            val action=DashboardFragmentDirections.actionDashboardFragmentToSubCategoryFragment(categories[position].categoryId)

            findNavController().navigate(action)
            

        }
    }


}