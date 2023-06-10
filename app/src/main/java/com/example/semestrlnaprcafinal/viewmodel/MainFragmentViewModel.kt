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


/**
 * ViewModel v ktorom uchovávam LiveData
 * pre moj main fragment,
 * používam MutableLiveData pretože sa časom
 * menia. Je to ako keby "mostík" medzi modelom a views.
 *
 * @constructor vytvorí prázdne "kontajnery" live dat.
 */
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

    /**
     * Metóda load data mi načítava
     * dáta z api klúča a uchováva mi ich
     * vo vyššie uvedených kontajneroch.
     *
     * @param cityName názov mesta, z ktorého chceme obdržať dáta o počasií
     *
     */


    fun loadData(cityName: String) {
        // získanie inštancie retrofitu, dát a vytvorenie
        val retrofit = RetrofitClass().getRetrofit()
        val response = retrofit.getData(cityName, ApiKey, Units)

        /**
         * Pokial získam odpoveď,
         * jednotlivé dáta sa mi načítajú do mojich vyššie
         * uvedených kontajnerov.
         *
         * @param call posle request do api v požadovanom tvare
         * @param response získanie odpovede v danom tvare
         *
         */

        response.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {

                val responseBody = response.body()

                if (responseBody != null) {

                    //nastavovanie hodnôt, ktoré získam z api
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

            /**
             * Pokial nedostanem response, zavola sa táto metoda
             * a error sa zapíše do logu
             *  @param call posle request do api v požadovanom tvare
             *  @throws t exception
             */
            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.d("DATA",t.toString())
            }

        })

    }
}