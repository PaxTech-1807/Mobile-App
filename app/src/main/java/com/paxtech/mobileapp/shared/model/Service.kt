package com.paxtech.mobileapp.shared.model

data class Service (
    val id: Int,
    val name: String,
    val duration: Int,
    val price: Double,
    val provider: String
)