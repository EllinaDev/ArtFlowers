package com.example.artflowersapp.repository

import com.example.artflowersapp.data.ArtDao
import com.example.artflowersapp.data.ArtModel
import javax.inject.Inject

class SetNewItemRepository @Inject constructor(private val artDao: ArtDao) {

    suspend fun insertNewItem(artModel: ArtModel) {
        artDao.insertNewItem(artModel)
    }
}