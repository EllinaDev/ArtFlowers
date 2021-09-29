package com.example.artflowersapp.utils

import com.example.artflowersapp.R
import com.example.artflowersapp.data.ArtModel

object FakeData {

    val flowers: List<ArtModel> = listOf(
        ArtModel(1, "rose", price = 1500, photo = R.drawable.flower),
        ArtModel(2,"pion",price = 2000, photo = R.drawable.flower_two),
        ArtModel(3,"rose",price = 35000, photo = R.drawable.flower_two),
        ArtModel(4,"pion",price = 4000, photo = R.drawable.flower),
        ArtModel(5,"pion",price = 2000, photo = R.drawable.flower_two),
        ArtModel(6,"rose",price = 2000, photo = R.drawable.flower_two),
        ArtModel(7,"pion",price = 2400, photo = R.drawable.flower),
        ArtModel(8,"pion",price = 9000, photo = R.drawable.flower_two),
        ArtModel(9,"rose",price = 6000, photo = R.drawable.flower),
        ArtModel(10,"pion",price = 2000, photo = R.drawable.flower_two)
    )
}