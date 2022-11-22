package com.example.bikesinnantes.model

import android.location.Location
import kotlinx.serialization.*

var currentLocation: Location? = null
var stationSelected:Station? = null
var allStations:List<Station>? = null

@Serializable
data class Station (
    val id: Long,
    val name: String,
    val lattitude: Double,
    val longitude: Double,
    val status: String,
    val address: String,
    val bikeStands: Long,
    val availableBikes: Long,
    val availableBikeStands: Long,
    val recordId: String
){

    fun toLocation(): Location {

        val location = Location("")

        location.latitude = lattitude
        location.longitude = longitude

        return location
    }

    fun showDetails(): CharSequence? {

        return "🚲${availableBikes} 📣${availableBikeStands} ✅${bikeStands}"
    }
}

var parkingSelected:Parking? = null
var allParkings:List<Parking>? = null

@Serializable
data class Parking (
    val id: Long,
    val grpDisponible: Long,
    val grpNom: String,
    val grpStatut: Long,
    val grpIdentifiant: String,
    val disponibilite: Int,
    val idobj: String,
    val grpComplet: Long,
    val grpExploitation: String,
    val latitude: Double,
    val longitude: Double,
    val recordId: String
){
    fun toLocation(): Location {

        val location = Location("")
        location.latitude = latitude
        location.longitude = longitude

        return location
    }

    fun showDetails(): CharSequence? {
        return "${disponibilite}/${grpComplet}"
    }
}


