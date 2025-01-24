package com.example.consumer_sales_blueex.screen

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.consumer_sales_blueex.R
import com.example.consumer_sales_blueex.databinding.ActivityCreateShipmentBinding
import com.example.consumer_sales_blueex.databinding.ActivityHomeBinding
import com.example.consumer_sales_blueex.screen.ktx.isVisible

class CreateShipmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateShipmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateShipmentBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        initSpinner()
        initViews()

    }

    private fun initViews(){

        binding.btBookNow.setOnClickListener {

            binding.llAddPickupDialog.root.isVisible()

        }
    }

    private fun initSpinner(){

        val country = listOf("Select a country", "Option 1", "Option 2", "Option 3")
        val destinationCity = listOf("Select a destination city", "Option 1", "Option 2", "Option 3")
        val service = listOf("Select a service", "Option 1", "Option 2", "Option 3")
        val originCity = listOf("Select a city", "Karachi", "Lahore", "Islamabad")
        val pickupLocation = listOf("Select an location", "Gulshan", "Johar", "Bahria town")

        val countryAdapter = ArrayAdapter(this, R.layout.spinner_item, country)
        val destinationCityAdapter = ArrayAdapter(this, R.layout.spinner_item, destinationCity)
        val serviceAdapter = ArrayAdapter(this, R.layout.spinner_item, service)
        val originCityAdapter = ArrayAdapter(this, R.layout.spinner_item, originCity)
        val pickupLocationAdapter = ArrayAdapter(this, R.layout.spinner_item, pickupLocation)

        binding.spCountry.adapter = countryAdapter
        binding.spDestinationCity.adapter = destinationCityAdapter
        binding.spService.adapter = serviceAdapter
        binding.spOriginCity.adapter = originCityAdapter
        binding.spPickupLocation.adapter = pickupLocationAdapter
    }

}