package com.example.e_commerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.constants.Constants
import com.example.e_commerceapp.databinding.ItemSubcategoryBinding
import com.example.e_commerceapp.model.ItemClickListener
import com.example.e_commerceapp.model.SubCategory
import com.example.e_commerceapp.util.placeImage

class SubCategoryAdapter(val list:List<SubCategory>):RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder>(), ItemClickListener{
    class SubCategoryViewHolder(val binding:ItemSubcategoryBinding):RecyclerView.ViewHolder(binding.root)

    private lateinit var onClickListener:(Int)->Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
       val binding=ItemSubcategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SubCategoryViewHolder(binding)
    }

    override fun getItemCount(): Int=list.size

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
       holder.binding.tvSubcategoryName.text=list[position].subCategoryName
        holder.binding.ivSubcategory.placeImage(Constants.IMAGE_URL+list[position].subCategoryImageUrl)
        holder.itemView.setOnClickListener{
            onClickListener(position)
        }
    }

    override fun ItemClickListener(listener:(position:Int)->Unit){
        onClickListener=listener

    }

}