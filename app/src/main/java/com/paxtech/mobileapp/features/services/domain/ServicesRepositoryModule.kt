package com.paxtech.mobileapp.features.services.domain

import com.paxtech.mobileapp.features.services.data.service.ServiceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ServicesRepositoryModule {

    @Binds
    fun provideServiceRepository(impl: ServiceRepositoryImpl): ServiceRepository
}