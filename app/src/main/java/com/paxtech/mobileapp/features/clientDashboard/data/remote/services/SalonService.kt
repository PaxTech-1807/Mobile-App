package com.paxtech.mobileapp.features.clientDashboard.data.remote.services

import com.paxtech.mobileapp.features.clientDashboard.data.remote.model.SalonDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SalonService {
    @GET("provider-profiles")
    suspend fun getAllSalons(): Response<List<SalonDto>?>

    @GET("provider-profiles/{id}")
    suspend fun getSalonById(@Path("id") id: Int): Response<SalonDto>
}