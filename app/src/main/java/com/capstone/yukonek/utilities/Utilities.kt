package com.capstone.yukonek.utilities

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class Utilities {
    fun convertDateString(input: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        parser.timeZone = TimeZone.getTimeZone("UTC")  // Ensure the UTC time is parsed correctly
        val date = parser.parse(input)

        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return formatter.format(date ?: return "")
    }

    fun formatSubscribers(count: Int): String {
        val format = NumberFormat.getNumberInstance(Locale.getDefault()) // Using German locale to use dot as thousand separator
        return "${format.format(count)} subscribers"
    }
}