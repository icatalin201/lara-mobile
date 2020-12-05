package com.mci.lara.mobile.view.room

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.databinding.ViewFeatureBinding
import com.mci.lara.mobile.util.DateExtensions.formatToString
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
Lara
Created by Catalin on 11/30/2020
 **/
class FeaturesAdapter(
    private val context: Context
) : RecyclerView.Adapter<FeaturesAdapter.FeaturesViewHolder>() {

    private val featureList = mutableListOf<Feature>()

    fun submitList(features: MutableList<Feature>) {
        featureList.clear()
        featureList.addAll(features)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ViewFeatureBinding = DataBindingUtil
            .inflate(inflater, R.layout.view_feature, parent, false)
        return FeaturesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeaturesViewHolder, position: Int) {
        holder.render(featureList[position])
    }

    override fun getItemCount(): Int {
        return featureList.size
    }

    inner class FeaturesViewHolder(
        private val binding: ViewFeatureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun render(feature: Feature) {
            val text = String.format("%.2f %s", feature.value, feature.unit)
            binding.featureTextTv.text = text
            binding.featureLastUpdateTv.text = context.getString(
                R.string.last_update,
                LocalDateTime.parse(
                    feature.recordedOn.replaceRange(19, feature.recordedOn.length, ""),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                ).atZone(ZoneOffset.UTC)
                    .withZoneSameInstant(ZoneId.of("Europe/Bucharest"))
                    .toLocalTime()
                    .formatToString()
            )
            binding.featureIconIv.setImageResource(feature.type.icon)
        }

    }
}