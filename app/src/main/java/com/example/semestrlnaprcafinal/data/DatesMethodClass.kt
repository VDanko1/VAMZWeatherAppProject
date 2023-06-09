package com.example.semestrlnaprcafinal.data

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Táto trieda obsahuje dve metódy - prvá mi vráti aktuálny dátum (dnešný)
 * a druhá metóda mi vráti zajtrajší dátum
 * obe vo formátoch "d.mm.yyyy".
 * Tieto metódy využívam vo fragmente days na predpoveď počasia.
 */


var dateFormat = "yyyy-MM-dd"

class DatesMethodClass {
    fun getTomorrowDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1) // Add 1 day to get tomorrow's date
        val dateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun getDayAfterTommorowDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 2) // Add 1 day to get tomorrow's date
        val dateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}