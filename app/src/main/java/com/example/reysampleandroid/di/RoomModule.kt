package com.example.reysampleandroid.di

import android.content.Context
import androidx.room.Room
import com.example.reysampleandroid.cacheDataSource.JokeCacheDatabase
import com.example.reysampleandroid.cacheDataSource.JokeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideJokeDB(@ApplicationContext context: Context): JokeCacheDatabase {
        return Room.databaseBuilder(
            context,
            JokeCacheDatabase::class.java,
            JokeCacheDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideJokeDao(jokeDb: JokeCacheDatabase): JokeDao {
        return jokeDb.jokeDao()
    }
}