package com.paxtech.mobileapp.features.clientDashboard.presentation.salondetail

import androidx.compose.runtime.*
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.paxtech.mobileapp.features.clientDashboard.presentation.details.ServiceUi

@Composable
fun SalonDetailRoute(
    salonId: Int,
    onBack: () -> Unit,
    onReserveService: (service: ServiceUi, salonName: String, salonAddress: String, salonRating: Double) -> Unit,
    viewModel: SalonDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(salonId) { viewModel.load(salonId) }

    val salon by viewModel.salon.collectAsState()
    val services by viewModel.services.collectAsState()
    val reviews by viewModel.reviews.collectAsState()
    val about by viewModel.about.collectAsState()

    val defaultAbout = about ?: com.paxtech.mobileapp.features.clientDashboard.presentation.details.AboutUi(
        description = "Información no disponible",
        schedule = listOf("Horario no especificado"),
        address = "Dirección no disponible",
        phone = "Teléfono no disponible"
    )

    val defaultServices = services.ifEmpty {
        listOf(
            ServiceUi("1", "Servicio no disponible", "Descripción no disponible", "s/0.00", 0)
        )
    }

    val defaultReviews = reviews.ifEmpty {
        listOf(
            com.paxtech.mobileapp.features.clientDashboard.presentation.details.ReviewUi("Cliente", 5, "No hay reseñas disponibles")
        )
    }

    SalonDetailScreen(
        salon = salon,
        services = defaultServices,
        reviews = defaultReviews,
        about = defaultAbout,
        onBack = onBack,
        onReserveService = { service ->
            onReserveService(
                service,
                salon?.companyName ?: "Salón",
                defaultAbout.address,
                4.7
            )
        }
    )
}