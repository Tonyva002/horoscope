package com.pangea.horoscope.domain.repository

import com.pangea.horoscope.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign: String): PredictionModel
}