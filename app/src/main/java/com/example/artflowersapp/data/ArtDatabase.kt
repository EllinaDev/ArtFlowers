package com.example.artflowersapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Database(entities = [ArtModel::class], version = 1, exportSchema = false)
abstract class ArtDatabase : RoomDatabase() {

    abstract fun artDao(): ArtDao

    companion object{
        @Volatile
        private var INSTANCE : ArtDatabase? = null

        fun getDatabase(context: Context): ArtDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArtDatabase::class.java,
                    "artdatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}