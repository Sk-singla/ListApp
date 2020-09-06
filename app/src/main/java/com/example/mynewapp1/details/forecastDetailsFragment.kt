package com.example.mynewapp1.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mynewapp1.*

class forecastDetailsFragment : Fragment() {

    private val args: forecastDetailsFragmentArgs by navArgs()
    private lateinit var TempDisplaySettingManager: tempDisplaySettingManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout =  inflater.inflate(R.layout.fragment_forecast_details,container,false)

        TempDisplaySettingManager = tempDisplaySettingManager(requireContext())

        val tempText = layout.findViewById<TextView>(R.id.tempText)
        val description = layout.findViewById<TextView>(R.id.descriptionText)


        tempText.text = formatTempForDisplay(args.temp, TempDisplaySettingManager.getTempDispalySetting())  // ° = alt + 248
        description.text = args.description


        return layout
    }

}