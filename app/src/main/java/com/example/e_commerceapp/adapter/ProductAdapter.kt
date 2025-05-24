package com.example.e_commerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.constants.Constants
import com.example.e_commerceapp.databinding.ProductViewBinding
import com.example.e_commerceapp.model.ItemClickListener
import com.example.e_commerceapp.model.OrderItem
import com.example.e_commerceapp.model.Product
import com.example.e_commerceapp.service.dblocal.Dao
import com.example.e_commerceapp.service.local.AppDatabase
import com.example.e_commerceapp.util.makeToast
import com.example.e_commerceapp.util.placeImage

class ProductAdapter(private val productList: List<Product>,val contex:Context) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        val database=AppDatabase.getAppDatabase(contex)
        val dao= database.getDao()


//    private lateinit var onClickListener:(Int)->Unit

    inner class ProductViewHolder(val binding: ProductViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.binding.apply {
            tvProductName.text = product.productName
            tvPrice.text = "$${product.price}"
            tvRating.text = "Rating: ${product.averageRating}"
            tvCategory.text = product.categoryName
            tvSubCategory.text = product.subCategoryName
            println(product.productImageUrl)
            ivProductImage.placeImage(Constants.IMAGE_URL+product.productImageUrl)

        }
        holder.binding.btnAddToCart.setOnClickListener {

            val orderItem=OrderItem(product.productId.toInt(),1,product.price.toInt())

           val result= dao.addOrder(orderItem)
            makeToast("Added to Cart",contex)



        }



//        holder.itemView.setOnClickListener{
//            onClickListener(position)
//        }
    }

//    override fun ItemClickListener(listener: (position: Int) -> Unit) {
//        onClickListener=listener
//    }
}
