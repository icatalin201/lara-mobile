package com.mci.lara.mobile.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.databinding.ActivityHomeBinding
import com.mci.lara.mobile.ui.room.RoomFragment
import org.koin.android.ext.android.inject

/**
 * Lara
 * Created by Catalin on 12/1/2020
 **/
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var roomsAdapter: RoomsAdapter
    private val viewModel: HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@HomeActivity,
            R.layout.activity_home
        )
        setupTabs()
        viewModel.rooms.observe(this) { consumeRooms(it) }
    }

    private fun setupTabs() {
        roomsAdapter = RoomsAdapter(supportFragmentManager)
        binding.homeViewPager.adapter = roomsAdapter
        binding.homeTabs.setupWithViewPager(binding.homeViewPager)
    }

    private fun consumeRooms(rooms: List<Room>) {
        val fragments = rooms.map { room -> RoomFragment.getInstance(room.id) }
        val titles = rooms.map { room -> room.name }
        roomsAdapter.submit(fragments, titles)
    }
}