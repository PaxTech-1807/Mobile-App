package com.paxtech.mobileapp.features.clientDashboard.domain.di

import com.paxtech.mobileapp.features.clientDashboard.data.repositories.SalonRepositoryImpl
import com.paxtech.mobileapp.features.clientDashboard.domain.domain.SalonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun provideProductRepository(impl: SalonRepositoryImpl): SalonRepository
}