package com.mci.lara.mobile.view.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.model.RoomType
import com.mci.lara.mobile.databinding.ViewRoomBinding
import com.squareup.picasso.Picasso

/**
 * Lara
 * Created by Catalin on 11/29/2020
 **/
class RoomsAdapter : RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>() {

    private val roomsList = mutableListOf<Room>()

    fun submitList(rooms: MutableList<Room>) {
        roomsList.clear()
        roomsList.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewRoomBinding = DataBindingUtil
            .inflate(inflater, R.layout.view_room, parent, false)
        return RoomsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.render(roomsList[position])
    }

    override fun getItemCount(): Int {
        return roomsList.size
    }

    inner class RoomsViewHolder(
        private val binding: ViewRoomBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun render(room: Room) {
            binding.viewRoomTv.text = room.name
            val image = when (room.type) {
                RoomType.BEDROOM -> R.drawable.bedroom
                RoomType.BATHROOM -> R.drawable.bathroom
                RoomType.KITCHEN -> R.drawable.kitchen
                RoomType.LIVING_ROOM -> R.drawable.livingroom
            }
            Picasso.get().load(image)
                .centerCrop()
                .fit()
                .into(binding.viewRoomIv)
        }

    }
}
