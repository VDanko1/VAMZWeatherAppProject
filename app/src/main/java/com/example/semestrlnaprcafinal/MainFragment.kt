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
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.semestrlnaprcafinal.WeatherData.Example
import com.example.semestrlnaprcafinal.data.ApiKey
import com.example.semestrlnaprcafinal.data.Units
import com.example.semestrlnaprcafinal.databinding.FragmentMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment(),LoadDataInterface {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData("Žilina")

        val nameOfCityToFindWeatherFor : EditText = binding.cityToFind

        val searchButton: ImageButton = binding.SearchButton
        searchButton.setOnClickListener {
            val pCityName = nameOfCityToFindWeatherFor.text.toString()
            loadData(pCityName)
        }

        val switchFor5Days: Switch = binding.DaysSwitch5
        switchFor5Days.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_daysFragment)
        }

    }

    override fun loadData(cityName: String) {
        val retrofit = RetrofitClass().getRetrofit()
        val response = retrofit.getData(cityName, ApiKey, Units)

        response.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>,response: Response<Example>) {

                val responseBody = response.body()

                if (responseBody != null) {
                    binding.LowestTemperature.text = responseBody.list.get(0).main.temp_min.toString()+ " °C"
                    binding.HighestTemperature.text = responseBody.list.get(0).main.temp_max.toString() + " °C"
                    binding.TemperatureNow.text = responseBody.list.get(0).main.temp.toString()+ " °C"
                    binding.HumidityVal.text = responseBody.list.get(0).main.humidity.toString()
                    binding.FeelsLikeTemperatureVal.text = responseBody.list.get(0).main.feels_like.toString()
                    binding.UVIndexVal.text = responseBody.list.get(0).weather.get(0).description
                    binding.WindSpeedVal.text = responseBody.list.get(0).wind.speed.toString()
                    binding.PressureVal.text = responseBody.list.get(0).main.pressure.toString()
                    binding.cityName.text = responseBody.city.name



                }
            }
            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.d("DATA",t.toString())
            }

        })

    }




}