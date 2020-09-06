package com.example.mynewapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.mynewapp1.forecast.CurrentForecastFragmentDirections
import com.example.mynewapp1.location.LocationEntryFragmentDirections
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity(), appNavigator {


    private lateinit var TempDisplaySettingManager: tempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"OnCreate",Toast.LENGTH_SHORT).show()

        TempDisplaySettingManager = tempDisplaySettingManager(this)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setupWithNavController(navController,appBarConfiguration)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //handle item selection
        return when(item.itemId){
            R.id.tempDisplaySetting -> {
                //do something
                showTempDisplaySettingDialog(this,TempDisplaySettingManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun navigateTocurrentForecast(zipcode: String) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragmentContainer, CurrentForecastFragment.newInstance(zipcode))
//            .commit()

        val action = LocationEntryFragmentDirections.actionLocationEntryFragmentToCurrentForecastFragment2()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateToLocationEntry() {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragmentContainer, LocationEntryFragment())
//            .commit()

        val action = CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateToForecastDetails(forecast: DailyForcast) {
        val action = CurrentForecastFragmentDirections.actionCurrentForecastFragmentToForecastDetailsFragment(forecast.temp,forecast.description)
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

}