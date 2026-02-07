package com.pangea.horoscope.domain.usecase

import com.pangea.horoscope.domain.model.PredictionModel
import com.pangea.horoscope.domain.repository.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(sign: String): PredictionModel =
        repository.getPrediction(sign)

}