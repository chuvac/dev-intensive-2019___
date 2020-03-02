package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun humanizeDiff(date: Date = Date()): String? {
    val differenceDate: Long = Date().time - date.time
    return when (differenceDate) {
        in 0 until SECOND -> "только что"
        in SECOND until 45* SECOND -> "несколько секунд назад"
        in 45* SECOND until 75* SECOND -> "минуту назад"
        in 75* SECOND until 45* MINUTE -> "${differenceDate/ MINUTE} ${humanizeWords(differenceDate / MINUTE, "минуту", "минуты", "минут")} назад"
        in 45* MINUTE until 75* MINUTE -> "час назад"
        in 75* MINUTE until 22* HOUR -> "${differenceDate/ HOUR} ${humanizeWords(differenceDate / HOUR, "час", "часа", "часов")} назад"
        in 22* HOUR until 26* HOUR -> "день назад"
        in 26* HOUR until 360* DAY -> "${differenceDate/ DAY} ${humanizeWords(differenceDate / DAY, "день", "дня", "дней")} назад"
        in 360 * DAY until Long.MAX_VALUE -> "более года назад"
        else -> null
    }
}

private fun humanizeWords(num : Long, one : String, two : String, five : String) : String{
    var n = num
    n %= 100
    if (n in 5..20) {
        return five
    }
    n %= 10
    if(n == 1L) {
        return one
    }
    if( n in 2..4) {
        return two
    }

    return five

}