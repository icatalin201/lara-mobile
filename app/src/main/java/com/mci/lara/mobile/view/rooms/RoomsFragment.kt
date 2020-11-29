package com.mci.lara.mobile.view.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mci.lara.mobile.R
import com.mci.lara.mobile.databinding.FragmentRoomsBinding

/**
Lara
Created by Catalin on 11/29/2020
 **/
class RoomsFragment : Fragment() {

    private lateinit var binding: FragmentRoomsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_rooms, container, false
        )
        return binding.root
    }

}