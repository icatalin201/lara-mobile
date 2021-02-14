package com.mci.lara.mobile.data.model

import java.util.*

/**
Lara
Created by Catalin on 11/18/2020
 **/
data class Feature(
    val id: UUID,
    val name: String,
    val type: FeatureType,
    val roomId: UUID,
    val lastRecordedData: FeatureData?
)