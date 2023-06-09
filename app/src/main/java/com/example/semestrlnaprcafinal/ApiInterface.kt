package com.example.semestrlnaprcafinal

import com.example.semestrlnaprcafinal.WeatherData.Example
import com.example.semestrlnaprcafinal.data.WeatherOrForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.openweathermap.org/data/2.5/forecast?q=Bratislava&appid=a4d960a92f7bc545787cbffcbdffc312&units=metric
interface ApiInterface {
    @GET(WeatherOrForecast)
    fun getData(@Query("q") city:String,
                @Query("appid") apikey:String,
                @Query("units") units:String):Call<Example>
}