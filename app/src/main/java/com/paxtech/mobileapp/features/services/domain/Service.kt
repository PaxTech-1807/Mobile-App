package com.paxtech.mobileapp.features.services.domain


data class Service (
    val id: Int,
    val name: String,
    val duration: Int,
    val price: Int,
    val providerId: Int
)
