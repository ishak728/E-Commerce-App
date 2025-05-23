package com.example.e_commerceapp.service.dblocal


object Tables {

    const val TABLE_NAME="orderItem"
    const val PRODUCT_ID="productID"
    const val QUANTITY="quantity"
    const val UNIT_PRICE="price"


    const val CREATE_TABLE="""
        CREATE TABLE $TABLE_NAME(
        $PRODUCT_ID INTEGER PRIMARY KEY,
        $QUANTITY INTEGER,
        $UNIT_PRICE INTEGER
        )
    """


}

