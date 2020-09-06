package com.example.mynewapp1.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewapp1.*
import com.example.mynewapp1.details.forecastDetailsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CurrentForecastFragment : Fragment() {
    private lateinit var TempDisplaySettingManager: tempDisplaySettingManager
    private val forecastRepository = ForcastRepository()

    private lateinit var  AppNavigator: appNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppNavigator = context as appNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val zipcode = requireArguments().getString(KEY_ZIPCODE) ?: ""

        // Inflate the layout for this fragment
        TempDisplaySettingManager = tempDisplaySettingManager(requireContext())
        val view= inflater.inflate(R.layout.fragment_current_forecast, container, false)

        val locationEntryButton:FloatingActionButton = view.findViewById(R.id.locationEntryButton)
        locationEntryButton.setOnClickListener{
            AppNavigator.navigateToLocationEntry()
        }


        val forecastList: RecyclerView = view.findViewById(R.id.foreCastList)
        forecastList.layoutManager = LinearLayoutManager(requireContext())

        val dailyForecastAdapter = dailyForecastAdapter(TempDisplaySettingManager) {
//            val msg = getString(R.string.forecast_clicked_format,it.temp, it.description)
//            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()

            showforecastDetails(it)  //forecast adapter item is passed in it
        }
        forecastList.adapter = dailyForecastAdapter



        val weeklyForecastObserver = Observer<List<DailyForcast>>{ ForecastItems->
            //update our list adapter
            dailyForecastAdapter.submitList(ForecastItems)
        }
        forecastRepository.weeklyForcast.observe(viewLifecycleOwner,weeklyForecastObserver)


        forecastRepository.loadForecast(zipcode)
        return view
    }

    private fun showforecastDetails( forecast: DailyForcast){
        AppNavigator.navigateToForecastDetails(forecast)
    }

    companion object{
        const val KEY_ZIPCODE ="key_zipcode"

        fun newInstance(zipcode: String): CurrentForecastFragment {
            val fragment = CurrentForecastFragment()

            val args = Bundle()
            args.putString(KEY_ZIPCODE,zipcode)
            fragment.arguments = args

            return fragment

        }
    }
}