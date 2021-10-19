package com.example.artflowersapp.data

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class ArtModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String? = null,
    val price: Int,
    val categoryId: Int? = null,
    val photo: Int? = null,
    val photoUri: String? = null,
    val size: String? = null,
    val composition: String? = null,
    val telNumber: Int? = null,
    val waNumber: Int? = null,
    val instAccount: String? = null,
    var likesCount: Int? = null
) : Parcelable {

    fun artToBasket(): BasketModel {
        return BasketModel(flower = this)
    }

}

data class FlowerCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)