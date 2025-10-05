package com.paxtech.mobileapp.features.clientDashboard.presentation.home

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.paxtech.mobileapp.shared.model.Salon

fun mockSalons(): List<Salon> = listOf(
    Salon(1, "Spa Serena", "https://images.unsplash.com/photo-1580618672591-eb180b1a973f?q=80&w=1200"),
    Salon(2, "Glam & Glow", "https://images.unsplash.com/photo-1556228578-8c89e6adf883?q=80&w=1200"),
    Salon(3, "Estilo & Belleza", "https://images.unsplash.com/photo-1582095133179-bfd08e2fc6b3?q=80&w=1200"),
    Salon(4, "Hair Loft", "https://images.unsplash.com/photo-1580136579312-94651dfd596d?q=80&w=1200"),
)

@Composable
fun Home(
    viewModel: ViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
){

}