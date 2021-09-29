package com.example.artflowersapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtModel (
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String? = null,
    val price: Int,
    val photo: Int? = null
    )