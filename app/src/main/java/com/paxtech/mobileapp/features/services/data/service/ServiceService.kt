package com.paxtech.mobileapp.features.services.data.service

import retrofit2.Response
import retrofit2.http.GET

interface ServiceService {
    @GET("api/v1/services")
    suspend fun getAllServices(): Response<List<ServiceDto>>
}