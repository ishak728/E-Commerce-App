package com.example.e_commerceapp.service.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.e_commerceapp.model.OrderItem

@Dao
interface OrderDao {

    @Insert
    fun addOrder(orderItem: OrderItem)

    @Query("SELECT * FROM order_item ")
    fun getOrders():List<OrderItem>
}