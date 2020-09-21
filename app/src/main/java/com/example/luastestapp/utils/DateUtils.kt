package com.example.luastestapp.utils

import org.joda.time.DateTime

object DateUtils {

    @JvmStatic
    fun currentlyBetweenMiddayAndMidnight(): Boolean {
        val currentTime = DateTime.now()
        val midday = DateTime().withHourOfDay(12).withMinuteOfHour(0)
        val midnight = DateTime().withTimeAtStartOfDay().plusDays(1)

        return currentTime.isAfter(midday) && currentTime.isBefore(midnight)
    }
}