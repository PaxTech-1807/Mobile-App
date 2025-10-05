// features/clientDashboard/presentation/details/SalonDetailRoute.kt
package com.paxtech.mobileapp.features.clientDashboard.presentation.salondetail

import androidx.compose.runtime.*
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.paxtech.mobileapp.features.clientDashboard.presentation.details.SalonDetailViewModel

@Composable
fun SalonDetailRoute(
    salonId: Int,
    onBack: () -> Unit,
    viewModel: SalonDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(salonId) { viewModel.load(salonId) }

    val salon by viewModel.salon.collectAsState()
    val services by viewModel.services.collectAsState()
    val reviews by viewModel.reviews.collectAsState()
    val about by viewModel.about.collectAsState()

    SalonDetailScreen(
        salon = salon,
        services = services,
        reviews = reviews,
        about = about,
        onBack = onBack
    )
}
