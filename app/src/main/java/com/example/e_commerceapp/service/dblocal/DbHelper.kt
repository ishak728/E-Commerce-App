package com.example.e_commerceapp.service.dblocal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context):SQLiteOpenHelper(context,"database",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(Tables.CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}