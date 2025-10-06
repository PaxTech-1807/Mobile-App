package com.paxtech.mobileapp.features.services.domain

interface ServiceRepository {
    suspend fun searchService(query: String): List<Service>
}