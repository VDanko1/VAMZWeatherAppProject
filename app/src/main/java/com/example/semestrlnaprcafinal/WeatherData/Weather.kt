package com.example.semestrlnaprcafinal.WeatherData


/**
 * Data class na udržiavanie
 * dát získaných pomocou api
 *
 * Jednotlivé data classes som získal z pluginu
 * s názvom Json to Data Class.
 */
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)