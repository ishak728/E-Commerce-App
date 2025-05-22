package com.example.e_commerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.constants.Constants
import com.example.e_commerceapp.databinding.ItemCategoryBinding
import com.example.e_commerceapp.model.Category
import com.example.e_commerceapp.model.ItemClickListener
import com.example.e_commerceapp.service.Retrofit
import com.example.e_commerceapp.util.placeImage

class CategoryAdapter(val categories:List<Category>):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() ,ItemClickListener{

    private lateinit var onClickListener:(Int)->Unit

    class CategoryViewHolder(val binding:ItemCategoryBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding=ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int=categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       val category=categories[position]
        holder.binding.tvCategoryName.text=category.categoryName
        val imageUrl = Constants.IMAGE_URL+ category.categoryImage
        holder.binding.ivCategoryImage.placeImage(imageUrl)

        holder.itemView.setOnClickListener{
           onClickListener(position)
        }
    }

    override fun ItemClickListener(listener:(position:Int)->Unit){
        onClickListener=listener

    }
}