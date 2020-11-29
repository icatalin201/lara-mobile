package com.mci.lara.mobile.view.devices

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Device
import com.mci.lara.mobile.databinding.ViewDeviceBinding

/**
 * Lara
 * Created by Catalin on 11/29/2020
 **/
class DevicesAdapter : RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder>() {

    private val deviceList = mutableListOf<Device>()

    fun submitList(devices: MutableList<Device>) {
        deviceList.clear()
        deviceList.addAll(devices)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDeviceBinding = DataBindingUtil
            .inflate(inflater, R.layout.view_device, parent, false)
        return DevicesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        holder.render(deviceList[position])
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

    inner class DevicesViewHolder(
        private val binding: ViewDeviceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun render(device: Device) {

        }

    }
}