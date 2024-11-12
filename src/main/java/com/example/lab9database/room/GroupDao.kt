package com.example.lab9database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GroupDao {

    @Insert
    suspend fun insert(group: Group)

    @Query("SELECT * FROM groups")
    suspend fun getAllGroups(): List<Group>
}
