package com.example.semestrlnaprcafinal

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.semestrlnaprcafinal.WeatherData.Example
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var minTemperatureTextView: TextView
    lateinit var maxTemperatureTextView: TextView
    lateinit var currentTemperatureTextView: TextView
    lateinit var feelsLikeTemperatureTextView: TextView
    lateinit var HumidityTextView: TextView
    lateinit var UvIndexTextView: TextView
    lateinit var windSpeedTextView: TextView
    lateinit var pressureTextView: TextView
    lateinit var cityTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData("Zilina")

        val nameOfCityToFindWeatherFor : EditText = view.findViewById(R.id.cityToFind)
        val searchButton: ImageButton = view.findViewById(R.id.SearchButton)
        searchButton.setOnClickListener {
            val pCityName = nameOfCityToFindWeatherFor.text.toString()
            loadData(pCityName)
        }

        val switchFor5Days: Switch = view.findViewById(R.id.DaysSwitch5)
        switchFor5Days.setOnClickListener() {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_daysFragment)
        }

        minTemperatureTextView = view.findViewById(R.id.LowestTemperature)
        maxTemperatureTextView = view.findViewById(R.id.HighestTemperature)
        currentTemperatureTextView = view.findViewById(R.id.TemperatureNow)
        feelsLikeTemperatureTextView = view.findViewById(R.id.FeelsLikeTemperatureVal)
        HumidityTextView = view.findViewById(R.id.HumidityVal)
        windSpeedTextView = view.findViewById(R.id.WindSpeedVal)
        pressureTextView = view.findViewById(R.id.PressureVal)
        UvIndexTextView = view.findViewById(R.id.UVIndexVal)
        cityTextView = view.findViewById(R.id.cityName)

    }

    private fun loadData(cityName: String) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val response = retrofit.getData(cityName, "a4d960a92f7bc545787cbffcbdffc312", "metric")

        response.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>,response: Response<Example>) {

                val responseBody = response.body()

                if (responseBody != null) {
                    minTemperatureTextView.text = responseBody.list.get(0).main.temp_min.toString()+ " °C"
                    maxTemperatureTextView.text = responseBody.list.get(0).main.temp_max.toString() + " °C"
                    currentTemperatureTextView.text = responseBody.list.get(0).main.temp.toString()+ " °C"
                    HumidityTextView.text = responseBody.list.get(0).main.humidity.toString()
                    feelsLikeTemperatureTextView.text = responseBody.list.get(0).main.feels_like.toString()
                    UvIndexTextView.text = "1"
                    windSpeedTextView.text = responseBody.list.get(0).wind.speed.toString()
                    pressureTextView.text = responseBody.list.get(0).main.pressure.toString()
                    cityTextView.text = responseBody.city.name
                }
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.d("DATA",t.toString())
            }

        })

    }




}