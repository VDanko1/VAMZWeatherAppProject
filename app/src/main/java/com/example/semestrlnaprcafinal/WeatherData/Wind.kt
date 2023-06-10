package com.example.semestrlnaprcafinal.WeatherData


/**
 * Data class na udržiavanie
 * dát získaných pomocou api
 *
 * Jednotlivé data classes som získal z pluginu
 * s názvom Json to Data Class.
 */

data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)