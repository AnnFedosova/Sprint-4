package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val resultZoneSet: MutableSet<String> = mutableSetOf()
    ZoneId.getAvailableZoneIds().forEach { id ->
        if (TimeZone.getTimeZone(id).rawOffset % 3600000 != 0) {
            resultZoneSet.add(id)
        }
    }
    return resultZoneSet
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val lastInMonthDayWeekList = mutableListOf<String>()
    (1..12).forEach { month ->
        val ld = LocalDate.of(year, month, 1)
        lastInMonthDayWeekList.add(ld.with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.name)
    }
    return lastInMonthDayWeekList
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var count = 0
    (1..12).forEach { month ->
        if (LocalDate.of(year, month, 13).dayOfWeek == DayOfWeek.FRIDAY) {
            count += 1
        }
    }
    return count
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US)
    return formatter.format(dateTime)
}



