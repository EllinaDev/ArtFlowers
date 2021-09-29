package com.example.artflowersapp.repository

import com.example.artflowersapp.data.ArtDao
import com.example.artflowersapp.data.ArtModel
import javax.inject.Inject

class ArtRepository @Inject constructor(val artDao: ArtDao) {

    fun selectAll() : List<ArtModel> = artDao.selectAll()

    fun insertName(artModel: ArtModel) {
        artDao.insertName(artModel)
    }
}