package com.mci.lara.mobile.view.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.databinding.FragmentRoomsBinding
import org.koin.android.ext.android.inject

/**
Lara
Created by Catalin on 11/29/2020
 **/
class RoomsFragment : Fragment() {

    private lateinit var binding: FragmentRoomsBinding
    private val viewModel: RoomsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_rooms, container, false
        )
        binding.roomsRv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getRoomList().observe(viewLifecycleOwner) { setRoomList(it) }
        return binding.root
    }

    private fun setRoomList(roomList: MutableList<Room>) {
        val adapter = binding.roomsRv.adapter
        if (adapter == null) {
            binding.roomsRv.adapter = RoomsAdapter()
        }
        (binding.roomsRv.adapter as RoomsAdapter).submitList(roomList)
        binding.roomsDisclaimerTv.isVisible = roomList.isEmpty()
    }

}