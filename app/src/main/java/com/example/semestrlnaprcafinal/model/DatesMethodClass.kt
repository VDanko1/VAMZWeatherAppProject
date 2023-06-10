package com.example.semestrlnaprcafinal.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Táto trieda obsahuje dve metódy - prvá mi vráti aktuálny dátum (dnešný)
 * a druhá metóda mi vráti zajtrajší dátum
 * obe vo formátoch "d.mm.yyyy".
 *
 * Tieto metódy využívam vo fragmente days na predpoveď počasia.
 *
 * inšpirácia (Nie prevziatie) - https://www.techiedelight.com/find-current-date-and-time-in-kotlin/
 */


class DatesMethodClass {
    private var dateFormat = "yyyy-MM-dd"
    /**
     * Vráti zajtrajší dátum v požadovanom formáte
     * @return string dátum vo formate
     */
    fun getTomorrowDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1) // Add 1 day to get tomorrow's date
        val dateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    /**
     * Vráti napozajtrajší dátum v požadovanom formáte
     * @return string dátum vo formate
     */

    fun getDayAfterTommorowDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 2) // Add 1 day to get tomorrow's date
        val dateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}