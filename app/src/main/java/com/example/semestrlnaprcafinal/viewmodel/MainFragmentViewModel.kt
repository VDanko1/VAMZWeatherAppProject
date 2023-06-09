package com.example.semestrlnaprcafinal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semestrlnaprcafinal.model.RetrofitClass
import com.example.semestrlnaprcafinal.WeatherData.Example
import com.example.semestrlnaprcafinal.model.ApiKey
import com.example.semestrlnaprcafinal.model.Units
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentViewModel : ViewModel() {

    val lowestTemperature: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val highestTemperature: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }
    val temperatureNow: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }
    val humidityVal: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val feelsLikeTemperatureNow: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val windSpeedVal: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }
    val pressureVal: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val cityName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val descriptionWeather: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    fun loadData(cityName: String) {
        val retrofit = RetrofitClass().getRetrofit()
        val response = retrofit.getData(cityName, ApiKey, Units)

        response.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {

                val responseBody = response.body()

                if (responseBody != null) {
                    lowestTemperature.value = responseBody.list.get(0).main.temp_min
                    highestTemperature.value = responseBody.list.get(0).main.temp_max
                    temperatureNow.value = responseBody.list.get(0).main.temp
                    humidityVal.value = responseBody.list.get(0).main.humidity
                    feelsLikeTemperatureNow.value = responseBody.list.get(0).main.feels_like
                    windSpeedVal.value = responseBody.list.get(0).wind.speed
                    pressureVal.value = + responseBody.list.get(0).main.pressure
                    this@MainFragmentViewModel.cityName.value = responseBody.city.name
                    descriptionWeather.value = "     " + responseBody.list.get(0).weather.get(0).description
                }
            }
            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.d("DATA",t.toString())
            }

        })

    }
}