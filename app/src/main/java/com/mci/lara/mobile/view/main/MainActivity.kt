package com.mci.lara.mobile.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.mci.lara.mobile.R
import com.mci.lara.mobile.databinding.ActivityMainBinding
import com.mci.lara.mobile.view.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.mainToolbar)
        setupNavigation()
    }

    private fun setupNavigation() {
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.home,
            R.id.rooms,
            R.id.devices,
            R.id.media,
            R.id.security
        ).build()
        navController = Navigation.findNavController(this, R.id.main_container)
        NavigationUI.setupWithNavController(binding.mainBottomNv, navController)
        NavigationUI.setupWithNavController(
            binding.mainToolbar,
            navController,
            appBarConfiguration
        )
    }
}