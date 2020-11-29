package com.mci.lara.mobile.view.devices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mci.lara.mobile.R
import com.mci.lara.mobile.databinding.FragmentDevicesBinding

/**
Lara
Created by Catalin on 11/29/2020
 **/
class DevicesFragment : Fragment() {

    private lateinit var binding: FragmentDevicesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_devices, container, false
        )
        return binding.root
    }

}