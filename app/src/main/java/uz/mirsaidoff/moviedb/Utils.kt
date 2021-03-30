package uz.mirsaidoff.moviedb

import java.lang.StringBuilder

object Utils {
    fun calculateDuration(minutes: Int): String {
        val sb = StringBuilder()
        if (minutes >= 60) {
            val hours = minutes / 60
            sb.append(hours.toString() + "h ")

            val mins = minutes - (hours * 60)
            sb.append(mins.toString() + "m")
        } else {
            sb.append(minutes.toString() + "m")
        }

        return sb.toString()
    }
}