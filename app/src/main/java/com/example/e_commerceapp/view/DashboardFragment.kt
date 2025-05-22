package com.example.e_commerceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.adapter.CategoryAdapter
import com.example.e_commerceapp.databinding.FragmentDashboardBinding
import com.example.e_commerceapp.model.Category
import com.example.e_commerceapp.repository.category.CategoryRepositoryImp
import com.example.e_commerceapp.service.Retrofit
import com.example.e_commerceapp.viewmodel.DashboardFragmentViewModel
import com.example.e_commerceapp.viewmodel.createFactory


class DashboardFragment : Fragment() {


    lateinit var binding: FragmentDashboardBinding
    lateinit var categories: List<Category>
    lateinit var adapter: CategoryAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel:DashboardFragmentViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = DashboardFragmentViewModel(
            CategoryRepositoryImp(Retrofit.api)
        ).createFactory()

        viewModel = ViewModelProvider(this, factory)[DashboardFragmentViewModel::class.java]

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

        viewModel.categories.observe(viewLifecycleOwner){
            categories = it
            adapter = CategoryAdapter(categories)
            recyclerView.adapter = adapter
            initializeCategoryOnClickListener()

        }

    }

    private fun initializeCategoryOnClickListener() {

        adapter.ItemClickListener { position ->



            val action=DashboardFragmentDirections.actionDashboardFragmentToSubCategoryFragment(categories[position].categoryId)

            findNavController().navigate(action)
            

        }
    }


}