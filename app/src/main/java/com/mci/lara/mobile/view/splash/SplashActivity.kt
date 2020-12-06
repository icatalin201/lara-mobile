package com.mci.lara.mobile.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mci.lara.mobile.R
import com.mci.lara.mobile.view.login.LoginActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.canEnterApp().observe(this) { handle(it) }
    }

    private fun handle(canEnter: Boolean) {
        if (canEnter) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}