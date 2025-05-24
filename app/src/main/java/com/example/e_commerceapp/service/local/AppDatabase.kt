package com.example.e_commerceapp.service.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_commerceapp.model.OrderItem

@Database(entities = [OrderItem::class], version = 1)
abstract class AppDatabase :RoomDatabase(){

    abstract fun getDao():OrderDao

    companion object{
        var instance:AppDatabase?=null

        fun getAppDatabase(context: Context):AppDatabase{
            if (instance==null){
                instance=Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "appdatabase")
                    .allowMainThreadQueries()
                    .build()


            }
            return instance!!

        }
    }
}