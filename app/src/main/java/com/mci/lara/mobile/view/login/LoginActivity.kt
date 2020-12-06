package com.mci.lara.mobile.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.mci.lara.mobile.R
import com.mci.lara.mobile.databinding.ActivityLoginBinding
import com.mci.lara.mobile.view.main.MainActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by inject()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_login)
        viewModel.isLoading().observe(this) { setLoading(it) }
        viewModel.isSuccess().observe(this) { setSuccess(it) }
        viewModel.isBiometricsEnabled().observe(this) { setBiometricsEnabled(it) }
        binding.loginBtn.setOnClickListener { viewModel.login() }
        binding.loginUsernameEt
            .addTextChangedListener { e -> viewModel.username = e.toString() }
        binding.loginPasswordEt
            .addTextChangedListener { e -> viewModel.password = e.toString() }
        binding.loginFingerprintBtn.setOnClickListener { openBiometricDialog() }
    }

    private fun setBiometricsEnabled(isEnabled: Boolean) {
        binding.loginFingerprintBtn.isVisible = isEnabled
        if (isEnabled) {
            openBiometricDialog()
        }
    }

    private fun openBiometricDialog() {
        viewModel.showBiometricPromptForDecryption(this)
    }

    private fun setLoading(isLoading: Boolean) {
        binding.loginLoading.visibility = when (isLoading) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
        binding.loginBtn.isEnabled = !isLoading
    }

    private fun setSuccess(success: Boolean) {
        if (success) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this,
                "Try again!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}