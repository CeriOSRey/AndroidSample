package com.example.reysampleandroid.cacheDataSource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JokeCacheEntity::class], version = 1)
abstract class JokeCacheDatabase: RoomDatabase() {
    abstract fun jokeDao(): JokeDao

    companion object {
        val DATABASE_NAME: String = "joke_db"
    }
}