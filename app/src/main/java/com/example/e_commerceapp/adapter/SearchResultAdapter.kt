package com.example.e_commerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.constants.Constants
import com.example.e_commerceapp.databinding.ItemProductBinding
import com.example.e_commerceapp.model.Product
import com.example.e_commerceapp.util.placeImage
import com.squareup.picasso.Picasso

class SearchResultAdapter( val products: MutableList<Product>) :
    RecyclerView.Adapter<SearchResultAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.binding.apply {
            tvProductName.text = product.productName
            tvProductPrice.text = "$${product.price}"
            tvProductDesc.text = product.description
            ivProductImage.placeImage(Constants.IMAGE_URL+product.productImageUrl)

        }
    }
}