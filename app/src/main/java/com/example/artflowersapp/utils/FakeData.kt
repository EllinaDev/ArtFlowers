package com.example.artflowersapp.utils

import com.example.artflowersapp.R
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.utils.FakeData.flowers

object FakeData {

    val flowers: List<ArtModel> = listOf(
        ArtModel(1, "rose", price = 1500, photo = R.drawable.flower),
        ArtModel(2,"pion",price = 2000, photo = R.drawable.d121b08d03dc88b44bda88fe92369d85),
        ArtModel(3,"pion",price = 35000, photo = R.drawable.d121b08d03dc88b44bda88fe92369d85),
        ArtModel(4,"pion",price = 4000, photo = R.drawable.flower),
        ArtModel(5,"pion",price = 2000, photo = R.drawable.d121b08d03dc88b44bda88fe92369d85),
        ArtModel(6,"pion",price = 2000, photo = R.drawable.d121b08d03dc88b44bda88fe92369d85),
        ArtModel(7,"pion",price = 2400, photo = R.drawable.flower),
        ArtModel(8,"pion",price = 9000, photo = R.drawable.d121b08d03dc88b44bda88fe92369d85),
        ArtModel(9,"pion",price = 6000, photo = R.drawable.flower),
        ArtModel(10,"pion",price = 2000, photo = R.drawable.d121b08d03dc88b44bda88fe92369d85)
    )
}