package com.mci.lara.mobile.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.mci.lara.mobile.R
import com.mci.lara.mobile.databinding.ActivityMainBinding
import com.mci.lara.mobile.view.login.LoginActivity
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            openSettings()
        } else if (item.itemId == R.id.logout) {
            openLogoutDialog()
        }
        return super.onOptionsItemSelected(item)
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

    private fun openSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun openLogoutDialog() {
        val dialog = AlertDialog.Builder(this, R.style.Theme_Lara_Dialog)
            .setTitle(R.string.logout)
            .setMessage(R.string.logout_confirmation_message)
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .setPositiveButton(R.string.confirm) { dialog, _ ->
                dialog.dismiss()
                closeAppAndOpenLogin()
            }
        dialog.show()
    }

    private fun closeAppAndOpenLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}