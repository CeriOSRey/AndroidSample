package com.example.reysampleandroid.cacheDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jokeEntity: JokeCacheEntity): Long

    @Query("SELECT * FROM jokes")
    suspend fun getJokes(): List<JokeCacheEntity>
}