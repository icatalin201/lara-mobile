package com.mci.lara.mobile.view.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.model.RoomType
import com.mci.lara.mobile.databinding.ActivityRoomBinding
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import java.util.*

class RoomActivity : AppCompatActivity() {

    companion object {
        const val ROOM_ID = "RoomId"
        const val ROOM_TYPE = "RoomType"
        const val ROOM_TITLE = "RoomTitle"
    }

    private lateinit var binding: ActivityRoomBinding
    private val viewModel: RoomViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_room)
        setCustomHeightForCover()
        setSupportActionBar(binding.roomToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
        val roomType = intent.getSerializableExtra(ROOM_TYPE) as RoomType
        val roomId = intent.getSerializableExtra(ROOM_ID) as UUID
        val roomTitle = intent.getStringExtra(ROOM_TITLE)
        title = roomTitle
        Picasso.get().load(roomType.image)
            .centerCrop()
            .fit()
            .into(binding.roomImageIv)
        binding.roomFeaturesRv.layoutManager = GridLayoutManager(this, 2)
        viewModel.getRoom().observe(this) { setRoom(it) }
        viewModel.getFeatureList().observe(this) { setFeatureList(it) }
        viewModel.fetch(roomId)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun setFeatureList(features: MutableList<Feature>) {
        val adapter = binding.roomFeaturesRv.adapter
        if (adapter == null) {
            binding.roomFeaturesRv.adapter = FeaturesAdapter()
        }
        (binding.roomFeaturesRv.adapter as FeaturesAdapter).submitList(features)
        binding.roomDisclaimerTv.isVisible = features.isEmpty()
    }

    private fun setRoom(room: Room) {
        binding.roomStatusTv.text = if (room.enabled) {
            getString(R.string.active)
        } else {
            getString(R.string.inactive)
        }
    }

    private fun setCustomHeightForCover() {
        val titleBarHeight = getStatusBarHeight()
        val params = binding.roomCoverCl.layoutParams as CollapsingToolbarLayout.LayoutParams
        params.bottomMargin = -titleBarHeight
        binding.roomCoverCl.layoutParams = params
    }

    private fun getStatusBarHeight(): Int {
        val resourceId = resources
            .getIdentifier("status_bar_height", "dimen", "android")
        return when (resourceId > 0) {
            true -> resources.getDimensionPixelSize(resourceId)
            else -> 0
        }
    }
}