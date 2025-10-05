// features/clientDashboard/presentation/details/SalonDetailViewModel.kt
package com.paxtech.mobileapp.features.clientDashboard.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paxtech.mobileapp.features.clientDashboard.domain.domain.SalonRepository
import com.paxtech.mobileapp.shared.model.Salon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SalonDetailViewModel @Inject constructor(
    private val repository: SalonRepository
) : ViewModel() {

    private val _salon = MutableStateFlow<Salon?>(null)
    val salon: StateFlow<Salon?> = _salon

    // Mocked tab data for now (easy to swap to repo later)
    private val _services = MutableStateFlow(mockServices())
    val services: StateFlow<List<ServiceUi>> = _services

    private val _reviews = MutableStateFlow(mockReviews())
    val reviews: StateFlow<List<ReviewUi>> = _reviews

    private val _about = MutableStateFlow(mockAbout())
    val about: StateFlow<AboutUi> = _about

    fun load(id: Int) {
        viewModelScope.launch {
            val result = repository.getSalonById(id)
            _salon.value = result ?: Salon(
                id = id,
                companyName = "Sin nombre",   // fallback if backend returns null
                coverImageUrl = ""
            )
        }
    }
}

// --- simple UI models (replace with DTO/domain later) ---
data class ServiceUi(
    val id: Int,
    val title: String,
    val subtitle: String,
    val price: String,
    val durationMins: Int
)
data class ReviewUi(
    val id: Int,
    val author: String,
    val rating: Int, // 0..5
    val comment: String
)
data class AboutUi(
    val description: String,
    val schedule: List<String>,
    val address: String,
    val phone: String
)

// --- mock data providers ---
private fun mockServices() = listOf(
    ServiceUi(1, "Corte simple", "Incluye lavado y secado básico.", "s/40.00", 50),
    ServiceUi(2, "Tratamiento de Keratina", "Incluye lavado y planchado", "s/50.00", 60),
    ServiceUi(3, "Color + Corte", "Color vegano + corte personalizado", "s/110.00", 90),
    ServiceUi(4, "Peinado para evento", "Ondas o alisado, fijación y asesoría", "s/110.00", 90),
)
private fun mockReviews() = listOf(
    ReviewUi(1, "Carla R.", 5, "Excelente servicio, muy puntual y el corte quedó tal como lo pedí. 100% recomendado"),
    ReviewUi(2, "Andrea M.", 3, "Buen ambiente y atención. El tinte me quedó muy bien, aunque la espera fue un poco larga."),
    ReviewUi(3, "Valeria G.", 4, "Me encantó el tratamiento de keratina, el cabello me quedó brillante y suave."),
)
private fun mockAbout() = AboutUi(
    description = "Salón especializado en cortes, coloración y tratamientos capilares. Combinamos talento, productos de alta calidad y un ambiente acogedor.",
    schedule = listOf("Lun–Sáb: 9:00 am – 8:00 pm", "Dom: 10:00 am – 4:00 pm"),
    address = "Av. Primavera 123, Santiago de Surco, Lima – Perú",
    phone = "+51 987654321"
)
