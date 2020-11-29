package com.mci.lara.mobile.view.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mci.lara.mobile.R
import com.mci.lara.mobile.databinding.FragmentSecurityBinding

/**
Lara
Created by Catalin on 11/29/2020
 **/
class SecurityFragment : Fragment() {

    private lateinit var binding: FragmentSecurityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_security, container, false
        )
        return binding.root
    }

}