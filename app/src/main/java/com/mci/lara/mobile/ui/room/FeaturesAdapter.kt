package com.mci.lara.mobile.ui.room

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.databinding.ViewFeatureBinding
import com.mci.lara.mobile.ext.DateExtensions.formatToString
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Lara
 * Created by Catalin on 2/14/2021
 **/
class FeaturesAdapter(
    private val context: Context
) : RecyclerView.Adapter<FeaturesAdapter.FeaturesViewHolder>() {

    private val features = mutableListOf<Feature>()

    fun submitList(features: List<Feature>) {
        this.features.clear()
        this.features.addAll(features)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ViewFeatureBinding = DataBindingUtil
            .inflate(inflater, R.layout.view_feature, parent, false)
        return FeaturesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeaturesViewHolder, position: Int) {
        holder.render(features[position])
    }

    override fun getItemCount(): Int {
        return features.size
    }

    inner class FeaturesViewHolder(
        private val binding: ViewFeatureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun render(feature: Feature) {
            val data = feature.lastRecordedData
            data?.let {
                val text = String.format("%.2f %s", data.value, feature.type.unit)
                binding.featureTextTv.text = text
                binding.featureCard.setOnClickListener {
                    val date = LocalDateTime.parse(
                        data.recordedOn.replaceRange(19, data.recordedOn.length, ""),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                    ).formatToString()
                    Toast.makeText(context, "Updated on: $date", Toast.LENGTH_SHORT).show()
                }
            }
            binding.featureIconIv.setImageResource(feature.type.icon)
        }

    }

}