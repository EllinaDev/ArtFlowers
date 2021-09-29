package com.example.artflowersapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtDao {
    @Query("SELECT * FROM ArtModel")
    fun selectAll(): List<ArtModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertName(artModel: ArtModel)
}