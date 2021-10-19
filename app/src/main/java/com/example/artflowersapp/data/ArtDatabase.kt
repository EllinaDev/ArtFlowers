package com.example.artflowersapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [ArtModel::class, BasketModel::class], version = 7, exportSchema = false)
@TypeConverters(FlowerTypeConverter::class)
abstract class ArtDatabase : RoomDatabase() {

    abstract fun artDao(): ArtDao

    abstract fun basketDao(): BasketDao

    companion object{
        @Volatile
        private var INSTANCE : ArtDatabase? = null

        fun getDatabase(context: Context): ArtDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArtDatabase::class.java,
                    "artdatabase"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }

}