package com.example.e_commerceapp.model



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("order_item")
data class OrderItem(
    @ColumnInfo(  "product_id")
    @SerializedName("product_id")
    @PrimaryKey(autoGenerate = false)
    val productId: Int,

    @ColumnInfo("quantity")
    @SerializedName("quantity")
    val quantity: Int,

    @ColumnInfo("unit_price")
    @SerializedName("unit_price")
    val unitPrice: Int
)
