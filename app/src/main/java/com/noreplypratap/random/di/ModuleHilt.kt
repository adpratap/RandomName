package com.noreplypratap.random.di

import com.noreplypratap.random.api.APIService
import com.noreplypratap.random.utils.Constants.Base_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ModuleHilt {

    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}