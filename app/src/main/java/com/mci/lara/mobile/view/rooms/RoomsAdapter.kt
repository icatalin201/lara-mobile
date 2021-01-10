package com.mci.lara.mobile.view.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.databinding.ViewRoomBinding
import com.squareup.picasso.Picasso

/**
 * Lara
 * Created by Catalin on 11/29/2020
 **/
class RoomsAdapter(
    private val listener: RoomClickListener
) : RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>() {

    private val roomList = mutableListOf<Room>()

    fun submitList(rooms: List<Room>) {
        roomList.clear()
        roomList.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewRoomBinding = DataBindingUtil
            .inflate(inflater, R.layout.view_room, parent, false)
        return RoomsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.render(roomList[position])
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    inner class RoomsViewHolder(
        private val binding: ViewRoomBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun render(room: Room) {
            binding.viewRoomTv.text = room.name
            Picasso.get().load(room.type.image)
                .centerCrop()
                .fit()
                .into(binding.viewRoomIv)
            binding.viewRoomCv.setOnClickListener { listener.onClick(room) }
        }

    }
}
