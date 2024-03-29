package com.example.semestrlnaprcafinal.view

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
import com.example.semestrlnaprcafinal.databinding.FragmentDaysBinding
import com.example.semestrlnaprcafinal.model.DefaultCityName
import com.example.semestrlnaprcafinal.viewmodel.FragmentDaysViewModel


/**
 * FragmentsDays mi zobrazuje
 * hodnoty prepovede počasia
 * na danom mieste.
 *
 * Defaulte po zapnutí aplikácie sa zobrazí
 * počasie pre Bratislavu.
 */

class FragmentDays : Fragment() {
    private var _binding: FragmentDaysBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FragmentDaysViewModel


    /**
     *  Funkcia OnCreate
     *  kde načítam dáta do svojho
     *  viewModelu a následne ich získavam
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(FragmentDaysViewModel::class.java)

        viewModel.loadData(DefaultCityName)

        loadDataFromViewModel()
    }

    /**
     * V funkcii onCreateView
     * si vytváram binding
     * na vyhladávanie jednotlivých textviews,edittextov... apod
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * V funkcii onViewCreated si nastavujem
     * navigáciu medzi mainFragmentom a framgnetomDays
     * Ďalej pridávam actionlistener na moj button,
     * pomocou ktorého vyhladávame počasie
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val oneDaySwitch : Switch = binding.SwitchDayOne
        oneDaySwitch.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_daysFragment_to_mainFragment)
        }

        val nameOfCityToFindWeatherFor : EditText = binding.cityToFind
        val searchButton: ImageButton = binding.SearchButton
        searchButton.setOnClickListener {
            DefaultCityName = nameOfCityToFindWeatherFor.text.toString() //prenastavi premennú na miesto ktore hladam
            viewModel.loadData(nameOfCityToFindWeatherFor.text.toString()) //načíta dáta do viewModelu
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
        viewModel.tomorrowDate.observe(this, Observer {
            binding.tommorowDate.text = it.toString()
        })

        viewModel.dayAfTomorrowDate.observe(this, Observer {
            binding.dayAfterTommorow.text = it.toString()
        })

        viewModel.tomorrowMorningValue.observe(this, Observer {
            binding.tomorrowMorningValue.text = it.toString()
        })

        viewModel.tomorrowNoonValue.observe(this, Observer {
            binding.tomorrowNoonValue.text = it.toString()
        })

        viewModel.tomorrowEveningValue.observe(this, Observer {
            binding.tomorrowEveningValue.text = it.toString()
        })

        viewModel.tomorrowNightValue.observe(this, Observer {
            binding.tomorrowNightValue.text = it.toString()
        })

        viewModel.tomorrowMorningDescription.observe(this, Observer {
            binding.tomorrowMorningDescription.text = it.toString()
        })

        viewModel.tomorrowNoonDescription.observe(this, Observer {
            binding.tomorrowNoonDescription.text = it.toString()
        })

        viewModel.tomorrowEveningDescription.observe(this, Observer {
            binding.tomorrowEveningDescription.text = it.toString()
        })

        viewModel.tomorrowNightDescription.observe(this, Observer {
            binding.tomorrowNightDescription.text = it.toString()
        })


        viewModel.dayAfTomorrowMorningValue.observe(this, Observer {
            binding.dayAfTomorrowMorningValue.text = it.toString()
        })

        viewModel.dayAfTomorrowNoonValue.observe(this, Observer {
            binding.dayAfTomorrowNoonValue.text = it.toString()
        })

        viewModel.dayAfTomorrowEveningValue.observe(this, Observer {
            binding.dayAfTomorrowEveningValue.text = it.toString()
        })

        viewModel.dayAfTomorrowNightValue.observe(this, Observer {
            binding.dayAfTomorrowNightValue.text = it.toString()
        })

        viewModel.tomorrowDayAfMorningDescription.observe(this, Observer {
            binding.tomorrowDayAfMorningDescription.text = it.toString()
        })

        viewModel.tomorrowDayAfNoonDescription.observe(this, Observer {
            binding.tomorrowDayAfNoonDescription.text = it.toString()
        })

        viewModel.tomorrowDayAfEveningDescription.observe(this, Observer {
            binding.tomorrowDayAfEveningDescription.text = it.toString()
        })

        viewModel.tomorrowDayAfNightDescription.observe(this, Observer {
            binding.tomorrowDayAfNightDescription.text = it.toString()
        })
    }
}