package com.example.bikesinnantes.api

import com.example.bikesinnantes.model.Parking
import retrofit2.Response
import retrofit2.http.GET

interface ParkingApi {

    @GET("api/parkings")
    suspend fun getParking():Response<List<Parking>>
}