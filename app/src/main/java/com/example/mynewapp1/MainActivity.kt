package com.example.mynewapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForcastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"OnCreate",Toast.LENGTH_SHORT).show()

        val zipCode: EditText = findViewById(R.id.ZipCode)

        
        val ClickButton: Button = findViewById(R.id.button)
        ClickButton.setOnClickListener{
            val s: String = zipCode.text.toString()
            forecastRepository.loadForecast(s)
        }

        val forecastList: RecyclerView = findViewById(R.id.foreCastList)
        forecastList.layoutManager = LinearLayoutManager(this)

        val dailyForecastAdapter = dailyForecastAdapter() {
            val msg = getString(R.string.forecast_clicked_format,it.temp, it.description)

            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
        }
        forecastList.adapter = dailyForecastAdapter



        val weeklyForecastObserver = Observer<List<DailyForcast>>{ForecastItems->
            //update our list adapter
            dailyForecastAdapter.submitList(ForecastItems)
        }
        forecastRepository.weeklyForcast.observe(this,weeklyForecastObserver)

    }

}