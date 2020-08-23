package com.example.mynewapp1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForcastRepository {

    private val _weeklyForecast = MutableLiveData<List<DailyForcast>>()
    val weeklyForcast: LiveData<List<DailyForcast>> = _weeklyForecast;

    fun loadForecast(zipcode: String){
        val randomValues = List(10){ Random.nextFloat().rem(100)*100}
        val forecastItems = randomValues.map {
            DailyForcast(it,getTempDescription(it))

        }

        _weeklyForecast.setValue(forecastItems)

    }

    private fun getTempDescription(temp: Float): String{
        return when(temp){
            in Float.MIN_VALUE.rangeTo(0f) -> "Anything below 0 doesn't make sense"
            in 0f.rangeTo(32f) -> "Its too cold"
            in 32f.rangeTo(55f) -> "Colder than I would prefer"
            in 55f.rangeTo(65f) -> "Getting Better"
            in 65f.rangeTo(80f) -> "That's the sweet spot!"
            in 80f.rangeTo(90f) -> "Getting a little warm"
            in 90f.rangeTo(100f) -> "Where's the A/C?"
            in 100f.rangeTo(Float.MAX_VALUE) -> "What is this, Arizona?"
            else-> "Does not compute"

        }
    }
}