package com.mci.lara.mobile.view.settings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mci.lara.mobile.R
import com.mci.lara.mobile.databinding.ActivitySettingsBinding
import org.koin.android.ext.android.inject

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val viewModel: SettingsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_settings)
        setSupportActionBar(binding.settingsToolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.isBiometricsEnabled()
            .observe(this) { binding.settingsBiometricsSwitch.isChecked = it }
        binding.settingsBiometricsSwitch.setOnClickListener {
            toggleBiometricsLogin(
                binding.settingsBiometricsSwitch.isChecked
            )
        }
    }

    private fun toggleBiometricsLogin(isOn: Boolean) {
        viewModel.toggleBiometrics(isOn)
        if (isOn) {
            showBiometricPromptForEncryption()
        }
    }

    private fun showBiometricPromptForEncryption() {
        viewModel.showBiometricPromptForEncryption(
            this,
            {
                binding.settingsBiometricsSwitch.isChecked = true
                Toast.makeText(applicationContext, R.string.biometrics_activated, Toast.LENGTH_LONG)
                    .show()
            },
            {
                binding.settingsBiometricsSwitch.isChecked = false
            })
    }
}