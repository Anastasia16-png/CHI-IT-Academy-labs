package com.example.lab9database.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Group::class, Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun studentDao(): StudentDao
}