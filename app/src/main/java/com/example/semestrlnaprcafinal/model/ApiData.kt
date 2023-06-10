package com.example.semestrlnaprcafinal.model


/**
 * Táto trieda slúži na udržiavanie konštánt.
 * Pretože ich využívam viackrát, je praktickejšie
 * ich zmeniť raz tu v triede ako viackrát v kóde.
 * Nachádza sa tu moj unikátny api klúč, jednotky
 * v ktorých získavam dáta, či chcem aktualne počasie
 * alebo predpoveď a defaultné nastavenie miesta
 * po spustení aplikácie.
 *
 */


const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val ApiKey = "a4d960a92f7bc545787cbffcbdffc312"
const val Units = "metric"
const val WeatherOrForecast = "forecast"
var DefaultCityName ="Bratislava"

class ApiData {
}