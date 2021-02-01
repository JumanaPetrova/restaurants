package com.example.restaurantapp.ui.base.utils

import java.text.SimpleDateFormat
import java.util.*

object SimpleDateFormatter {
    const val FORMAT_DATE = "dd.MM.yyyy HH:mm"
    private val ruLocale = Locale("ru", "RU")

    fun format(date: Date, format: String = FORMAT_DATE): String {
        return SimpleDateFormat(format, ruLocale).apply {
            timeZone = TimeZone.getDefault()
        }.format(date)
    }
}