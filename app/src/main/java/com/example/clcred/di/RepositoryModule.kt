package com.example.clcred.di

import com.example.clcred.data.repository.DataRepositoryImpl
import com.example.clcred.model.DataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        dataRepositoryImpl: DataRepositoryImpl
    ): DataRepository
}