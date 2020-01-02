package com.example.getaround_test

/*
*
* NavHost
*
* */

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    override fun onSupportNavigateUp() =
        findNavController(this, R.id.nav_host_fragment).navigateUp()

    override fun onBackPressed() {
      super.onBackPressed()
    }

    //TODO 👇
    private fun setupNavigation() {
        val navController = findNavController(this, R.id.nav_host_fragment)
    }
}
