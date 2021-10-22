package com.example.artflowersapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArtDao {

    @Query("SELECT * FROM ArtModel")
    fun selectAll(): LiveData<List<ArtModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewItem(artModel: ArtModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(artModel: List<ArtModel>)

    @Query("SELECT *FROM ArtModel WHERE name LIKE :query")
    suspend fun search(query: String): List<ArtModel>

    @Insert
    suspend fun addBaseFlowers(flowers: List<ArtModel>)

    @Delete
    fun deleteItem(artModel: ArtModel)

    @Query("SELECT *FROM ArtModel WHERE composition MATCH :query")
    fun getSimilarQuery(query: String): List<ArtModel>

    @Update(entity = ArtModel::class)
    suspend fun updateLike(likes: Likes)
}

data class Likes(
    val id: Int,
    val likesCount: Int = 0
)