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


/**
 * MainFragment mi zobrazuje
 * aktuálne počasie v danom mieste
 *
 * Defaulte po zapnutí aplikácie sa zobrazí
 * počasie pre Bratislavu.
 */

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainFragmentViewModel

    /**
     *  Funkcia OnCreate
     *  kde načítam dáta do svojho
     *  viewModelu a následne ich získavam
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainFragmentViewModel::class.java)

        viewModel.loadData(DefaultCityName)

        loadDataFromViewModel()
    }

    /**
     * V funkcii onCreateView
     * si vytváram binding
     * na vyhladávanie jednotlivých textviews,edittextov... apod
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * V funkcii onViewCreated si nastavujem
     * navigáciu medzi mainFragmentom a framgnetomDays
     *
     * Ďalej pridávam actionlistener na moj button,
     * pomocou ktorého vyhladávame počasie.
     */

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameOfCityToFindWeatherFor : EditText = binding.cityToFind

        val searchButton: ImageButton = binding.SearchButton
        searchButton.setOnClickListener {
            DefaultCityName = nameOfCityToFindWeatherFor.text.toString() //prenastavi premennú na miesto ktore hladam
            viewModel.loadData(nameOfCityToFindWeatherFor.text.toString()) //načíta dáta do viewModelu
        }

        val switchFor5Days: Switch = binding.DaysSwitch5
        switchFor5Days.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_daysFragment)
        }
    }

    /**
     * Pri prechode medzi fragmentmi alebo vytvorení inštancie
     * MainFragment sa mi načitajú dáta
     * z mojho ViewModelu ktorý patri k tomuto fragmentu
     *
     * Observer mi automatiky načíta dáta
     * pri zmene. To znamená že nemusím túto metódu
     * opakovane volať ale automaticky po zmené dát vo ViewModeli
     * sa mi dáta aktualizujú.
     */

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