package com.example.artflowersapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.artflowersapp.data.ArtModel

@Entity(tableName = "basket")
data class BasketModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val flower: ArtModel
)