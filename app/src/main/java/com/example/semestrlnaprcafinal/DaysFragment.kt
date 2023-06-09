package com.example.semestrlnaprcafinal

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
import com.example.semestrlnaprcafinal.data.DatesMethodClass
import com.example.semestrlnaprcafinal.data.Units
import com.example.semestrlnaprcafinal.databinding.FragmentDaysBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DaysFragment : Fragment(), LoadDataInterface {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentDaysBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData("Žilina")
        super.onViewCreated(view, savedInstanceState)
        val datesObject = DatesMethodClass()

        binding.tommorowDate.text = datesObject.getTomorrowDate()
        binding.dayAfterTommorow.text = datesObject.getDayAfterTommorowDate()

        val oneDaySwitch : Switch = binding.SwitchDayOne
        oneDaySwitch.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_daysFragment_to_mainFragment)
        }

        val nameOfCityToFindWeatherFor : EditText = binding.cityToFind

        val searchButton: ImageButton = binding.SearchButton
        searchButton.setOnClickListener {
            val pCityName = nameOfCityToFindWeatherFor.text.toString()
            loadData(pCityName)
        }
    }

    override fun loadData(cityName: String) {
        val retrofit = RetrofitClass().getRetrofit()
        val response = retrofit.getData(cityName, ApiKey, Units)
        val datesObject = DatesMethodClass()

        val tomorrowDateString = datesObject.getTomorrowDate()

        var indexOfTTomorrowApi = 0

        response.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    for(i in 1..10) {
                        val tomorrowDateFromApi = responseBody.list[i].dt_txt.substring(0, 10)
                        if(tomorrowDateFromApi == tomorrowDateString) {
                            indexOfTTomorrowApi = i
                            println(indexOfTTomorrowApi)
                            break
                        }
                    }

                    binding.tomorrowMorningValue.text = responseBody.list.get(indexOfTTomorrowApi+2).main.temp.toString() + "°C"
                    binding.tomorrowNoonValue.text = responseBody.list.get(indexOfTTomorrowApi+4).main.temp.toString()+ "°C"
                    binding.tomorrowEveningValue.text = responseBody.list.get(indexOfTTomorrowApi+6).main.temp.toString() + "°C"
                    binding.tomorrowNightValue.text = responseBody.list.get(indexOfTTomorrowApi+8).main.temp.toString()+ "°C"

                    binding.tomorrowMorningDescription.text = responseBody.list.get(indexOfTTomorrowApi+2).weather.get(0).description
                    binding.tomorrowNoonDescription.text = responseBody.list.get(indexOfTTomorrowApi+4).weather.get(0).description
                    binding.tomorrowEveningDescription.text = responseBody.list.get(indexOfTTomorrowApi+6).weather.get(0).description
                    binding.tomorrowNightDescription.text = responseBody.list.get(indexOfTTomorrowApi+8).weather.get(0).description


                    binding.dayAfTomorrowMorningValue.text = responseBody.list.get(indexOfTTomorrowApi+9).main.temp.toString() + "°C"
                    binding.dayAfTomorrowNoonValue.text = responseBody.list.get(indexOfTTomorrowApi+11).main.temp.toString()+ "°C"
                    binding.dayAfTomorrowEveningValue.text = responseBody.list.get(indexOfTTomorrowApi+13).main.temp.toString() + "°C"
                    binding.dayAfTomorrowNightValue.text = responseBody.list.get(indexOfTTomorrowApi+15).main.temp.toString()+ "°C"

                    binding.tomorrowDayAfMorningDescription.text = responseBody.list.get(indexOfTTomorrowApi+9).weather.get(0).description
                    binding.tomorrowDayAfNoonDescription.text = responseBody.list.get(indexOfTTomorrowApi+11).weather.get(0).description
                    binding.tomorrowDayAfEveningDescription.text = responseBody.list.get(indexOfTTomorrowApi+13).weather.get(0).description
                    binding.tomorrowDayAfNightDescription.text = responseBody.list.get(indexOfTTomorrowApi+15).weather.get(0).description

                }
            }
            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.d("DATA",t.toString())
            }

        })

    }


}