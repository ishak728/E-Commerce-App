package com.example.e_commerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.SubCategoryAdapter
import com.example.e_commerceapp.databinding.FragmentSubCategoryBinding
import com.example.e_commerceapp.model.SubCategory
import com.example.e_commerceapp.model.SubCategoryResponse
import com.example.e_commerceapp.service.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SubCategoryFragment : Fragment() {
    lateinit var binding:FragmentSubCategoryBinding
    lateinit var adapter: SubCategoryAdapter
    lateinit var recyclerView:RecyclerView
    lateinit var subCategory:List<SubCategory>

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSubCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryId= arguments?.getString("categoryId")

        recyclerView=binding.rvSubCategory
        recyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        if (categoryId != null) {
            getCategory(categoryId)
        }



    }

    private fun moveToProductFragment() {
       adapter.ItemClickListener { position ->
           val action=SubCategoryFragmentDirections.actionSubCategoryFragmentToProductFragment(subCategory[position].subCategoryId)
           findNavController().navigate(action)

       }
    }

    fun getCategory(categoryId:String){
        val call=Retrofit.api.getSubCategory(categoryId)

        call.enqueue(object :Callback<SubCategoryResponse>{
            override fun onResponse(
                call: Call<SubCategoryResponse>,
                response: Response<SubCategoryResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                     subCategory = response.body()!!.subCategory
                    adapter=SubCategoryAdapter(subCategory)
                    recyclerView.adapter=adapter
                    moveToProductFragment()


                }
            }

            override fun onFailure(call: Call<SubCategoryResponse>, t: Throwable) {
                println(t.localizedMessage)

            }
        })
    }





}