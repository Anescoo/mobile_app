package com.example.bikesinnantes.api

import com.example.bikesinnantes.model.Station
import retrofit2.Response
import retrofit2.http.GET

interface StationApi {

    @GET("api/stations")
    suspend fun getStation():Response<List<Station>>
}