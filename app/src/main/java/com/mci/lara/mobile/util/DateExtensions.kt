package com.mci.lara.mobile.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Lara
 * Created by Catalin on 12/3/2020
 **/
object DateExtensions {

    fun LocalTime.formatToString(): String {
        return this.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
    }

}