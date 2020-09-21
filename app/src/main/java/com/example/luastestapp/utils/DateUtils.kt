package com.example.luastestapp.utils

import org.joda.time.DateTime

object DateUtils {

    @JvmStatic
    fun isAM(): Boolean {
        val currentTime = DateTime.now()

        if (currentTime.hourOfDay == 12) {
            return currentTime.minuteOfHour == 0
        }

        return currentTime.hourOfDay < 12
    }
}