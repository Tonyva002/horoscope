package com.pangea.horoscope.data.network

import com.pangea.horoscope.data.network.response.PredictionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign: String): Response<PredictionResponse>
}