package com.mci.lara.mobile.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mci.lara.mobile.R
import com.mci.lara.mobile.view.login.LoginActivity
import com.mci.lara.mobile.view.main.MainActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.isLoggedIn().observe(this) { handle(it) }
    }

    private fun handle(isLoggedIn: Boolean) {
        val intent = when (isLoggedIn) {
            true -> Intent(this, MainActivity::class.java)
            else -> Intent(this, LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

}