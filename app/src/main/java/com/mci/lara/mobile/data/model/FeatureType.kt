package com.mci.lara.mobile.data.model

import com.mci.lara.mobile.R

/**
Lara
Created by Catalin on 11/24/2020
 **/
enum class FeatureType(val icon: Int, val unit: String) {
    TEMPERATURE(R.drawable.ic_thermometer, "°C"),
    HUMIDITY(R.drawable.ic_humidity, "%")
}