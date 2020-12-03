package com.mci.lara.mobile.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Lara
 * Created by Catalin on 12/3/2020
 **/
object DateExtensions {

    fun LocalDateTime.formatToString(): String {
        return this.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
    }

}