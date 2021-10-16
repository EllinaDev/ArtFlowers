package com.example.artflowersapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFlowerIntoBasket(flower: BasketModel)

    @Query("SELECT * FROM basket")
    fun selectAllFlowerFromBasket(): LiveData<List<BasketModel>>

    @Delete
    suspend fun deleteFlowerFromBasket(basketModel: BasketModel)
}