package com.pangea.horoscope.data.repository

import com.pangea.horoscope.data.network.ApiService
import com.pangea.horoscope.domain.model.DomainError
import com.pangea.horoscope.domain.model.PredictionModel
import com.pangea.horoscope.domain.repository.Repository
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {
    override suspend fun getPrediction(sign: String): PredictionModel {
        try {
            val response = apiService.getHoroscope(sign)
            if (!response.isSuccessful) {
                throw when (response.code()) {
                    404 -> DomainError.NotFound()
                    in 500..599 -> DomainError.Server()
                    else -> DomainError.Unknown()

                }
            }
            return response.body()?.toDomain()
                ?: throw DomainError.Unknown()

        } catch (e: java.io.IOException) {
            throw DomainError.Network()
        }


    }
}