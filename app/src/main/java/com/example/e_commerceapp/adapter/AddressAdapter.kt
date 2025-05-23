package com.example.e_commerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.databinding.ItemUserAddressBinding
import com.example.e_commerceapp.model.UserAddress

class AddressAdapter(private val address: List<UserAddress>): RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    class AddressViewHolder(val binding:ItemUserAddressBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding=ItemUserAddressBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddressViewHolder(binding)
    }

    override fun getItemCount(): Int =address.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {

        holder.binding.tvAddressTitle.text=address[position].title
        holder.binding.tvAddressDetail.text=address[position].address
    }

}