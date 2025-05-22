package com.example.e_commerceapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ActivityHostBinding
import com.example.e_commerceapp.model.SearchListener

class HostActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHostBinding
    lateinit var appBarConfig:AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

         initialize()

        setSupportActionBar(binding.toolbar)



        val navCon=(supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment).navController

        appBarConfig=AppBarConfiguration(
            setOf(R.id.dashboardFragment,R.id.cartFragment,R.id.ordersFragment,R.id.profileFragment,R.id.logoutFragment,),
            binding.drawerLayout
        )

        NavigationUI.setupActionBarWithNavController(this,navCon,appBarConfig)
        NavigationUI.setupWithNavController(binding.navigationView,navCon)








    }

    private fun initialize() {
        binding.ivSearch.setOnClickListener {

            val query=binding.etSearch.text.toString()


            val bundle = Bundle().apply {
                putString("query", query)
            }

            val navController = findNavController(R.id.fragmentContainerView2)
            navController.navigate(R.id.searchResultFragment, bundle)



        }

        binding.ivClear.setOnClickListener {
            binding.etSearch.setText("")
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView2)
        return NavigationUI.navigateUp(navController, appBarConfig) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.search->{
                if (binding.searchBarLayout.isVisible){
                    binding.searchBarLayout.visibility=View.GONE
                }else{
                    binding.searchBarLayout.visibility=View.VISIBLE
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }




}