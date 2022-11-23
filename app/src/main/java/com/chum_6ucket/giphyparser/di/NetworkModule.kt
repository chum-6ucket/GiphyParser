package com.chum_6ucket.giphyparser.di

import com.chum_6ucket.giphyparser.data.api.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGifService(): NetworkService {
        return NetworkService.create()
    }
}