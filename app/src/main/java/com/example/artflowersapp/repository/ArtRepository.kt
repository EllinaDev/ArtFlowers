package com.example.artflowersapp.repository

import androidx.lifecycle.LiveData
import com.example.artflowersapp.data.*
import javax.inject.Inject

class ArtRepository @Inject constructor(private val artDao: ArtDao, private val basketDao: BasketDao) {

    fun selectAll() : LiveData<List<ArtModel>> = artDao.selectAll()

    suspend fun addFlowerIntoBasket(artModel: ArtModel) {
        basketDao.addFlowerIntoBasket(artModel.artToBasket())
    }

    fun getAllFlowersFromBasket() = basketDao.selectAllFlowerFromBasket()

    suspend fun deleteFromBasket(basketModel: BasketModel) {
        basketDao.deleteFlowerFromBasket(basketModel)
    }

    suspend fun search(query: String) : List<ArtModel> {
        return artDao.search(query)
    }

    suspend fun addBaseDataToRoom(flowers: List<ArtModel>) {
        artDao.addBaseFlowers(flowers)
    }

    fun deleteItem(artModel: ArtModel) {
        artDao.deleteItem(artModel)
    }

    fun getSimilarQuery(categoryId: Int, id: Int) : List<ArtModel> {
        return artDao.getSimilarQuery(categoryId, id)
    }

    suspend fun updateLikes(likes: Likes) {
        artDao.updateLike(likes)
    }

    suspend fun insertAll(artModel: List<ArtModel>) {
        artDao.insertAll(artModel)
    }
}