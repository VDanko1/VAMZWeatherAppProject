package com.example.semestrlnaprcafinal.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.semestrlnaprcafinal.R
import com.example.semestrlnaprcafinal.databinding.FragmentMainBinding
import com.example.semestrlnaprcafinal.model.DefaultCityName
import com.example.semestrlnaprcafinal.viewmodel.MainFragmentViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel = ViewModelProvider(requireActivity()).get(MainFragmentViewModel::class.java)

        viewModel.loadData(DefaultCityName)

        loadDataFromViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameOfCityToFindWeatherFor : EditText = binding.cityToFind

        val searchButton: ImageButton = binding.SearchButton
        searchButton.setOnClickListener {
            val pCityName = nameOfCityToFindWeatherFor.text.toString()
            DefaultCityName = pCityName
            viewModel.loadData(pCityName)
        }

        val switchFor5Days: Switch = binding.DaysSwitch5
        switchFor5Days.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_daysFragment)
        }
    }

    private fun loadDataFromViewModel() {
        viewModel.lowestTemperature.observe(this, Observer {
            binding.LowestTemperature.text = it.toString()
        })

        viewModel.highestTemperature.observe(this, Observer {
            binding.HighestTemperature.text = it.toString()
        })

        viewModel.pressureVal.observe(this, Observer {
            binding.PressureVal.text = it.toString()
        })

        viewModel.temperatureNow.observe(this, Observer {
            binding.TemperatureNow.text = it.toString()
        })

        viewModel.feelsLikeTemperatureNow.observe(this, Observer {
            binding.FeelsLikeTemperatureVal.text = it.toString()
        })

        viewModel.windSpeedVal.observe(this, Observer {
            binding.WindSpeedVal.text = it.toString()
        })

        viewModel.descriptionWeather.observe(this, Observer {
            binding.UVIndexVal.text = it
        })

        viewModel.cityName.observe(this, Observer {
            binding.cityName.text = it
        })

        viewModel.humidityVal.observe(this, Observer {
            binding.HumidityVal.text = it.toString()
        })
    }
}