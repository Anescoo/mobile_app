package com.example.bikesinnantes.ui.stationDetail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.bikesinnantes.R
import com.example.bikesinnantes.model.stationSelected

class StationDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_detail)

        val stationName = findViewById<TextView>(R.id.stationName)
        val button = findViewById<Button>(R.id.buttonOpenMap)

        if (stationSelected != null){

        }
        stationSelected?.let { station ->
            stationName.text = station.name
            button.setOnClickListener {
                // Display a label at the location of Google's Sydney office
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${station.lattitude},${station.longitude}(${station.name})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }
}