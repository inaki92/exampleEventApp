package com.example.todoappcat23

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.todoappcat23.databinding.FragmentCountriesBinding

class CountriesFragment : Fragment() {

    private val binding  by lazy {
        FragmentCountriesBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // list of countries
        val countries = mutableListOf(
            "USA",
            "SPAIN",
            "CANADA",
            "BRAZIL",
            "CHINA",
            "GERMANY",
            "ENGLAND",
            "FRANCE"
        )

        // here we are accessing the list view in the Fragment
        binding.countriesList.apply {
            // here I am setting the adapter
            adapter = ArrayAdapter(
                context,
                R.layout.country_item,
                countries
            )
        }

        return binding.root
    }

    companion object {
        fun newInstance() = CountriesFragment()
    }
}