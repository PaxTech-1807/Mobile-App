package com.paxtech.mobileapp.features.services.data.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceRemoteModule {

    @Provides
    @Singleton
    fun providesServiceService(retrofit: Retrofit): ServiceService {
        return retrofit.create(ServiceService::class.java)
    }
}