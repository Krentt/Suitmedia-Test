package com.example.suitmedia.di

import android.content.Context
import com.example.suitmedia.data.UserRepository
import com.example.suitmedia.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}