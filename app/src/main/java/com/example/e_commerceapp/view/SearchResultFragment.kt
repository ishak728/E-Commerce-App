package com.example.e_commerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.adapter.SearchResultAdapter
import com.example.e_commerceapp.databinding.FragmentSearchResultBinding
import com.example.e_commerceapp.model.SearchListener
import com.example.e_commerceapp.model.SearchResponse
import com.example.e_commerceapp.service.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchResultFragment : Fragment() {
    lateinit var binding:FragmentSearchResultBinding
    lateinit var adapter:SearchResultAdapter
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSearchResultBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val query= arguments?.getString("query")

        if (query != null) {
            searchQuery(query)
        }

        recyclerView=binding.rvSearchResults
        recyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        adapter=SearchResultAdapter(arrayListOf())

    }

     fun searchQuery(query: String) {

        val call= Retrofit.api.searchProduct(query)

        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val products = response.body()!!.products
                    adapter.products.clear()
                    adapter.products.addAll(products)
                    recyclerView.adapter=adapter
                    adapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                println(t.localizedMessage)
            }
        })
    }
}