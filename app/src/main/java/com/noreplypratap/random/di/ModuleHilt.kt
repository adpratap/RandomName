package com.noreplypratap.random.di

import android.content.Context
import com.noreplypratap.random.api.APIService
import com.noreplypratap.random.network.NetworkManager
import com.noreplypratap.random.utils.Constants.Base_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleHilt {

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideIsConnectionStatus(@ApplicationContext context: Context) : NetworkManager  {
        return NetworkManager(context)
    }

}