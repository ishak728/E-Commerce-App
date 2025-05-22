package com.example.e_commerceapp.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.example.e_commerceapp.R
import com.squareup.picasso.Picasso


fun makeToast(message: String,context: Context) {
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
}



fun ImageView.placeImage(imageUrl:String){
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .into(this)
}