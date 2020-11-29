package com.mci.lara.mobile.data.model

import java.time.LocalDateTime
import java.util.*

/**
Lara
Created by Catalin on 11/18/2020
 **/
data class Feature(
        val id: UUID,
        val active: Boolean,
        val name: String,
        val type: FeatureType,
        val value: Double,
        val recordedOn: LocalDateTime
)