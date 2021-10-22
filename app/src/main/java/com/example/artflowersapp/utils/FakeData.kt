package com.example.artflowersapp.utils

import com.example.artflowersapp.R
import com.example.artflowersapp.data.ArtModel

object FakeData {

    val flowers: List<ArtModel> = listOf(
        ArtModel(1, "Микс букет", price = 2000, categoryId = 5, composition = "5 кремовых роз, 3 ветки нежной эустомы,\\n5 веток кустовой розы, альстромерии, зелень, оформление может отличаться.", size ="высота 50 см, ширина 40 см" ,description = "Чарующий и необычайно нежный букет навеет романтическое настроение и подарит незабываемые впечатления! " , photo = R.drawable.flower_two)
    )
}