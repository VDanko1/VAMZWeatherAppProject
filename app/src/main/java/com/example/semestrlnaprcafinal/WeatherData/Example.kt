package com.example.semestrlnaprcafinal.WeatherData


/**
 * Data class na udržiavanie
 * dát získaných pomocou api
 *
 * Jednotlivé data classes som získal z pluginu
 * s názvom Json to Data Class.
 */
data class Example(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DT>,
    val message: Int
)