package com.mci.lara.mobile.view.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mci.lara.mobile.R
import com.mci.lara.mobile.databinding.ActivityRegisterBinding
import com.mci.lara.mobile.view.login.LoginActivity
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by inject()
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_register)
        viewModel.isLoading().observe(this) { setLoading(it) }
        viewModel.isSuccess().observe(this) { setSuccess(it) }
        binding.registerBtn.setOnClickListener { viewModel.register() }
        binding.registerLoginBtn.setOnClickListener { openLogin() }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.registerLoading.visibility = when (isLoading) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
        binding.registerBtn.isEnabled = !isLoading
        binding.registerLoginBtn.isEnabled = !isLoading
    }

    private fun setSuccess(success: Boolean) {
        if (success) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this,
                "Try again!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}