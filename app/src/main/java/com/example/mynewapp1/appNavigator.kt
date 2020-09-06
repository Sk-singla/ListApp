package com.example.mynewapp1

interface appNavigator {
    fun navigateTocurrentForecast(zipcode: String)

    fun navigateToLocationEntry()

    fun navigateToForecastDetails(forecast: DailyForcast)
}