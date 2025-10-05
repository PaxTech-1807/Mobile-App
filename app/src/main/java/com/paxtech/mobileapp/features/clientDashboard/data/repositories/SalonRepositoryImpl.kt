package com.paxtech.mobileapp.features.clientDashboard.data.repositories

import com.paxtech.mobileapp.features.clientDashboard.data.remote.services.SalonService
import com.paxtech.mobileapp.features.clientDashboard.domain.domain.SalonRepository
import com.paxtech.mobileapp.shared.model.Salon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SalonRepositoryImpl @Inject constructor(
    private val service: SalonService
): SalonRepository {
    override suspend fun getAllSalons(): List<Salon> = withContext(Dispatchers.IO){
        val resp = service.getAllSalons()
        if (resp.isSuccessful){
            val body = resp.body().orEmpty()
            return@withContext body.map { dto ->
                Salon(
                    dto.id ?: 0,
                    dto.companyName.orEmpty(),
                    dto.coverImageUrl.orEmpty()
                )
            }
        }
        emptyList()
    }

    // SalonRepositoryImpl.kt
    override suspend fun getSalonById(id: Int): Salon? = withContext(Dispatchers.IO) {
        try {
            val resp = service.getSalonById(id)
            if (!resp.isSuccessful) return@withContext null
            resp.body()?.let { dto ->
                Salon(
                    id = dto.id ?: 0,
                    companyName = dto.companyName.orEmpty(),
                    coverImageUrl = dto.coverImageUrl.orEmpty()
                )
            }
        } catch (e: Exception) {
            null
        }
    }


}