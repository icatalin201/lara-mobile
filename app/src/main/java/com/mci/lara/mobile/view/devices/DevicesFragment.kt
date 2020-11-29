package com.mci.lara.mobile.view.devices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Device
import com.mci.lara.mobile.databinding.FragmentDevicesBinding
import org.koin.android.ext.android.inject

/**
Lara
Created by Catalin on 11/29/2020
 **/
class DevicesFragment : Fragment() {

    private lateinit var binding: FragmentDevicesBinding
    private val viewModel: DevicesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_devices, container, false
        )
        binding.devicesRv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getDeviceList().observe(viewLifecycleOwner) { setDeviceList(it) }
        return binding.root
    }

    private fun setDeviceList(deviceList: MutableList<Device>) {
        val adapter = binding.devicesRv.adapter
        if (adapter == null) {
            binding.devicesRv.adapter = DevicesAdapter()
        }
        (binding.devicesRv.adapter as DevicesAdapter).submitList(deviceList)
        binding.devicesDisclaimerTv.isVisible = deviceList.isEmpty()
    }

}