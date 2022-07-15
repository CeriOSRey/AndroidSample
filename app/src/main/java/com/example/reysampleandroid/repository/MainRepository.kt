package com.example.reysampleandroid.repository

import android.provider.ContactsContract
import com.example.reysampleandroid.cacheDataSource.CacheMapper
import com.example.reysampleandroid.cacheDataSource.JokeDao
import com.example.reysampleandroid.model.Joke
import com.example.reysampleandroid.networkDataSource.JokeNetworkInterface
import com.example.reysampleandroid.networkDataSource.NetworkMapper
import com.example.reysampleandroid.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MainRepository
    constructor(
        private val jokeDao: JokeDao,
        private val jokeNetworkInterface: JokeNetworkInterface,
        private val cacheMapper: CacheMapper,
        private val networkMapper: NetworkMapper
    )
{
        suspend fun getJoke(): Flow<DataState<Joke>> = flow {
            emit(DataState.Loading)
            delay(1000)
            try {
                val networkJoke = jokeNetworkInterface.getJoke()      // get joke from network
                val joke = networkMapper.mapFromEntity(networkJoke)   // map from networkEntity to domain model
                val cacheJoke = cacheMapper.mapToEntity(joke)    //convert to cacheEntity
                jokeDao.insert(cacheJoke)                             // save to cache
                val fromCacheEntities = jokeDao.getJokes()            // fetch all jokes from cache
                val cacheJokes = cacheMapper.mapFromEntityList(fromCacheEntities) // convert to domain model from cacheEntity
                cacheJokes.forEach {
                    emit(DataState.Success(it))
                }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
}