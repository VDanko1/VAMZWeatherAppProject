package com.example.semestrlnaprcafinal.WeatherData


/**
 * Data class na udržiavanie
 * dát získaných pomocou api
 *
 * Jednotlivé data classes som získal z pluginu
 * s názvom Json to Data Class.
 */
data class DT (
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)