package com.example.clcred.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.clcred.dB.ComposeSkeltonDB
import com.example.clcred.data.local.RoomDao
import com.example.clcred.data.remote.ApiService
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
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ComposeSkeltonDB =
        Room.databaseBuilder(app, ComposeSkeltonDB::class.java, "my_database").build()

    @Provides
    fun provideMyDao(database: ComposeSkeltonDB): RoomDao = database.myDao()

    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context = context
}