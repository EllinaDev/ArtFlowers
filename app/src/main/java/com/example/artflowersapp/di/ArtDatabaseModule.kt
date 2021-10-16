package com.example.artflowersapp.di

import android.content.Context
import com.example.artflowersapp.data.ArtDao
import com.example.artflowersapp.data.ArtDatabase
import com.example.artflowersapp.data.BasketDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArtDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : ArtDatabase {
        return ArtDatabase.getDatabase(context)
    }

    @Provides
    fun provideArtDao(database: ArtDatabase): ArtDao{
        return database.artDao()
    }

    @Provides
    fun provideBasketDao(database: ArtDatabase): BasketDao{
        return database.basketDao()
    }
    
}