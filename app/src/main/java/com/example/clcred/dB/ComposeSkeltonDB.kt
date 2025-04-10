package com.example.clcred.dB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clcred.data.local.RoomDao
import com.example.clcred.model.dataClass.SpinItem


@Database(entities = [SpinItem::class], version = 1, exportSchema = false)
abstract class ComposeSkeltonDB : RoomDatabase() {
    abstract fun myDao(): RoomDao
}