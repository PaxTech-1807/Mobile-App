package com.paxtech.mobileapp.features.clientDashboard.domain.domain

import com.paxtech.mobileapp.shared.model.Salon

interface SalonRepository {
    suspend fun getAllSalons(): List<Salon>

    suspend fun getSalonById(id: Int): Salon?
}