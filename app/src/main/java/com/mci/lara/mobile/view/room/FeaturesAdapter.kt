package com.mci.lara.mobile.view.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mci.lara.mobile.R
import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.databinding.ViewFeatureBinding

/**
Lara
Created by Catalin on 11/30/2020
 **/
class FeaturesAdapter : RecyclerView.Adapter<FeaturesAdapter.FeaturesViewHolder>() {

    private val featureList = mutableListOf<Feature>()

    fun submitList(features: MutableList<Feature>) {
        featureList.clear()
        featureList.addAll(features)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
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
            val text = "${feature.value} ${feature.unit}"
            binding.featureTextTv.text = text
            binding.featureIconIv.setImageResource(feature.type.icon)
        }

    }
}