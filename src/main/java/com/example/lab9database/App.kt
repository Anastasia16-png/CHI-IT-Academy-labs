package com.example.lab9database

import android.app.Application
import androidx.room.Room
import com.example.lab9database.room.AppDatabase

class App : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app_database").build()
    }
}