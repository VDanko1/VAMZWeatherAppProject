package com.example.semestrlnaprcafinal.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semestrlnaprcafinal.WeatherData.Example
import com.example.semestrlnaprcafinal.model.ApiKey
import com.example.semestrlnaprcafinal.model.DatesMethodClass
import com.example.semestrlnaprcafinal.model.RetrofitClass
import com.example.semestrlnaprcafinal.model.Units
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentDaysViewModel : ViewModel() {
    val dayAfTomorrowDate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tomorrowDate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tomorrowMorningDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tomorrowNoonDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tomorrowEveningDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tomorrowNightDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tomorrowDayAfMorningDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tomorrowDayAfNoonDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tomorrowDayAfEveningDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tomorrowDayAfNightDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tomorrowMorningValue: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val tomorrowNoonValue: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val tomorrowEveningValue: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val tomorrowNightValue: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }


    val dayAfTomorrowMorningValue: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val dayAfTomorrowNoonValue: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val dayAfTomorrowEveningValue: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val dayAfTomorrowNightValue: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }


    fun loadData(cityName: String) {
        val retrofit = RetrofitClass().getRetrofit()
        val response = retrofit.getData(cityName, ApiKey, Units)
        val getDatesClass = DatesMethodClass()

        var indexOfTTomorrowApi = 0

        response.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    for(i in 1..10) {
                        val tomorrowDateFromApi = responseBody.list[i].dt_txt.substring(0, 10)
                        if(tomorrowDateFromApi == getDatesClass.getTomorrowDate()) {
                            indexOfTTomorrowApi = i
                            println(indexOfTTomorrowApi)
                            break
                        }
                    }

                    tomorrowDate.value = getDatesClass.getTomorrowDate()
                    dayAfTomorrowDate.value = getDatesClass.getDayAfterTommorowDate()

                    tomorrowMorningValue.value = responseBody.list.get(indexOfTTomorrowApi+2).main.temp
                    tomorrowNoonValue.value = responseBody.list.get(indexOfTTomorrowApi+4).main.temp
                    tomorrowEveningValue.value = responseBody.list.get(indexOfTTomorrowApi+6).main.temp
                    tomorrowNightValue.value = responseBody.list.get(indexOfTTomorrowApi+8).main.temp

                    tomorrowMorningDescription.value = responseBody.list.get(indexOfTTomorrowApi+2).weather.get(0).description
                    tomorrowNoonDescription.value = responseBody.list.get(indexOfTTomorrowApi+4).weather.get(0).description
                    tomorrowEveningDescription.value = responseBody.list.get(indexOfTTomorrowApi+6).weather.get(0).description
                    tomorrowNightDescription.value = responseBody.list.get(indexOfTTomorrowApi+8).weather.get(0).description

                    dayAfTomorrowMorningValue.value = responseBody.list.get(indexOfTTomorrowApi+9).main.temp
                    dayAfTomorrowNoonValue.value = responseBody.list.get(indexOfTTomorrowApi+11).main.temp
                    dayAfTomorrowEveningValue.value = responseBody.list.get(indexOfTTomorrowApi+13).main.temp
                    dayAfTomorrowNightValue.value = responseBody.list.get(indexOfTTomorrowApi+15).main.temp

                    tomorrowDayAfMorningDescription.value = responseBody.list.get(indexOfTTomorrowApi+9).weather.get(0).description
                    tomorrowDayAfNoonDescription.value = responseBody.list.get(indexOfTTomorrowApi+11).weather.get(0).description
                    tomorrowDayAfEveningDescription.value = responseBody.list.get(indexOfTTomorrowApi+13).weather.get(0).description
                    tomorrowDayAfNightDescription.value = responseBody.list.get(indexOfTTomorrowApi+15).weather.get(0).description

                }
            }
            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.d("DATA",t.toString())
            }

        })

    }




}