package com.example.e_commerceapp.model

import com.google.gson.annotations.SerializedName
data class ProductDetail(
    @SerializedName("product_id")
    val productId: String,

    @SerializedName("product_name")
    val productName: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("category_id")
    val categoryId: String,

    @SerializedName("sub_category_id")
    val subCategoryId: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("average_rating")
    val averageRating: String,

    @SerializedName("product_image_url")
    val productImageUrl: String,

    @SerializedName("is_active")
    val isActive: String,

    @SerializedName("images")
    val images: List<ProductImage>,

    @SerializedName("specifications")
    val specifications: List<ProductSpecification>,

    @SerializedName("reviews")
    val reviews: List<ProductReview>
)
