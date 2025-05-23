package com.example.e_commerceapp.service.dblocal

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.e_commerceapp.model.OrderItem

class Dao(context: Context) {

    val helper = DbHelper(context)
    val db = helper.writableDatabase

    fun addOrder(order: OrderItem): Boolean {

        val value = ContentValues()
        value.put(Tables.PRODUCT_ID, order.productId)
        value.put(Tables.UNIT_PRICE, order.unitPrice)
        value.put(Tables.QUANTITY, order.quantity)


        val result = db.insert(Tables.TABLE_NAME, null, value)

        return result != -1L
    }


    @SuppressLint("Range")
    fun getOrders(): List<OrderItem> {
        val orderList = mutableListOf<OrderItem>()
        val cursor = db.query(Tables.TABLE_NAME, null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val productId = cursor.getInt(cursor.getColumnIndex(Tables.PRODUCT_ID))
            val quantity = cursor.getInt(cursor.getColumnIndex(Tables.QUANTITY))
            val unitPrice = cursor.getInt(cursor.getColumnIndex(Tables.UNIT_PRICE))

            val order = OrderItem(
                productId,
                quantity,
                unitPrice
            )

            orderList.add(order)
        }


        return orderList
    }


}