package com.example.artflowersapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson


class FlowerTypeConverter {

    // model to json
    @TypeConverter
    fun fromArtModel(model: ArtModel) = Gson().toJson(model)

    // json to data class
    @TypeConverter
    fun toArtModel(s: String) = Gson().fromJson(s, ArtModel::class.java)

}