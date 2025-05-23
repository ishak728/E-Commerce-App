package com.example.e_commerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.databinding.ItemCartBinding
import com.example.e_commerceapp.model.ProductDetail
import com.example.e_commerceapp.util.placeImage


class CartAdapter(private val products: List<ProductDetail>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = products[position]
        var quantity = 1

        with(holder.binding) {
            tvProductName.text = product.productName
            tvPrice.text = "$${product.price}"
            tvQuantity.text = quantity.toString()
            ivProductImage.placeImage(product.productImageUrl)

            btnIncrease.setOnClickListener {
                quantity++
                tvQuantity.text = quantity.toString()

            }

            btnDecrease.setOnClickListener {
                if (quantity > 1) {
                    quantity--
                    tvQuantity.text = quantity.toString()
                }
            }
        }
    }

    override fun getItemCount(): Int = products.size
}
