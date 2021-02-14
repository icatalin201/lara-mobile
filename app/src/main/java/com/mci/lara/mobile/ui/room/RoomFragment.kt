package com.mci.lara.mobile.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.databinding.FragmentRoomBinding
import org.koin.android.ext.android.inject
import java.util.*

/**
 * Lara
 * Created by Catalin on 2/14/2021
 **/
class RoomFragment : Fragment() {

    private lateinit var binding: FragmentRoomBinding
    private lateinit var featuresAdapter: FeaturesAdapter
    private val viewModel: RoomViewModel by inject()

    companion object {

        private const val ROOM_ID = "room_id"

        fun getInstance(roomId: UUID): RoomFragment {
            val fragment = RoomFragment()
            fragment.arguments = bundleOf(Pair(ROOM_ID, roomId))
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_room,
            container,
            false
        )
        setupRoomsRecycler()
        viewModel.features.observe(viewLifecycleOwner) { consumeFeatures(it) }
        val roomId = arguments?.get(ROOM_ID) as UUID
        viewModel.getFeatures(roomId)
        return binding.root
    }

    private fun setupRoomsRecycler() {
        featuresAdapter = FeaturesAdapter(requireContext())
        binding.featuresRecycler.adapter = featuresAdapter
        binding.featuresRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun consumeFeatures(features: List<Feature>) {
        featuresAdapter.submitList(features)
    }

}