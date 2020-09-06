package com.example.mynewapp1.location

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mynewapp1.R
import com.example.mynewapp1.appNavigator


class LocationEntryFragment : Fragment() {

    private lateinit var AppNavigator: appNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppNavigator = context as appNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_location_entry, container, false)

        //update ui
        val zipCode: EditText = view.findViewById(R.id.ZipCode)


        val ClickButton: Button = view.findViewById(R.id.button)


        ClickButton.setOnClickListener{
            val zipcode: String = zipCode.text.toString()
            if(zipcode.length !=5){
                Toast.makeText(requireContext(),"zipcode Entry Error", Toast.LENGTH_SHORT).show()
            }
            else{
//                Toast.makeText(requireContext(),"Zipcode Entered", Toast.LENGTH_SHORT).show()
//                forecastRepository.loadForecast(zipcode)
                AppNavigator.navigateTocurrentForecast(zipcode)
            }

        }

        return view
    }

}