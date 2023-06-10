package com.example.semestrlnaprcafinal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.semestrlnaprcafinal.R


class FragmentSearch : Fragment() {
    private val capCities = listOf("Tokyo", "Bratislava", "Prague", "Warsaw", "Zilina")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val autoComplete: AutoCompleteTextView = view.findViewById(R.id.autoCompleteTextView)
        val searchBut: Button = view.findViewById(R.id.SearchCityButton)
        val textFromAutoComplete = autoComplete.getText()
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, capCities)
        autoComplete.setAdapter(adapter)


        searchBut.setOnClickListener() {
                Navigation.findNavController(view)
                    .navigate(R.id.action_fragmentSearch_to_mainFragment)
            }
    }
}