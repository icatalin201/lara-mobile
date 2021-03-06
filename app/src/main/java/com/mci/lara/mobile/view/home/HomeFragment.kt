package com.mci.lara.mobile.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.House
import com.mci.lara.mobile.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

/**
Lara
Created by Catalin on 11/29/2020
 **/
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        viewModel.getHouse().observe(viewLifecycleOwner) { setHouse(it) }
        return binding.root
    }

    private fun setHouse(house: House) {
        binding.homeRoomTv.text = house.name
    }

}