package com.example.semestrlnaprcafinal.WeatherData


/**
 * Data class na udržiavanie
 * dát získaných pomocou api
 *
 * Jednotlivé data classes som získal z pluginu
 * s názvom Json to Data Class.
 */
data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)