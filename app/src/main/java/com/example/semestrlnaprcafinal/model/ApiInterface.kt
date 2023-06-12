package com.example.semestrlnaprcafinal.model

import com.example.semestrlnaprcafinal.WeatherData.Example
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.openweathermap.org/data/2.5/forecast?q=Bratislava&appid=a4d960a92f7bc545787cbffcbdffc312&units=metric


/**
 * Interface mi slúži na "budovanie linku"
 * resp. v akom formáte získavam dáta z api.
 * príklad je uvedený nižšie.
 *
 * https://api.openweathermap.org/data/2.5/forecast?q=Bratislava&appid=a4d960a92f7bc545787cbffcbdffc312&units=metric
 *
 * @param city mesto v kde chceme vyhladat pocasie
 * @param appid api klúč
 * @param units v akych jednotkach chceme dáta získať
 *
 * @return Vráti nám dataclass obsahujúci informacie o počasí
 */

interface ApiInterface {
    /*
       inšpirácia (nie prevzatie) - https://www.youtube.com/watch?v=RSYTn-O3r34&t=1161s&ab_channel=ResoCoder
     */
    @GET(WeatherOrForecast)
    fun getData(@Query("q") city:String,
                @Query("appid") apikey:String,
                @Query("units") units:String):Call<Example>
}