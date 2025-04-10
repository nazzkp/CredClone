package com.example.clcred.data.repository

import android.app.Application
import com.example.clcred.data.local.RoomDao
import com.example.clcred.data.remote.ApiService
import com.example.clcred.model.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: RoomDao,
    private val appContext: Application
): DataRepository {



}