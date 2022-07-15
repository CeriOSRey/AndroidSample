package com.example.reysampleandroid.di

import com.example.reysampleandroid.cacheDataSource.CacheMapper
import com.example.reysampleandroid.cacheDataSource.JokeDao
import com.example.reysampleandroid.networkDataSource.JokeNetworkInterface
import com.example.reysampleandroid.networkDataSource.NetworkMapper
import com.example.reysampleandroid.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        jokeDao: JokeDao,
        jokeNetworkInterface: JokeNetworkInterface,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(jokeDao, jokeNetworkInterface, cacheMapper, networkMapper)
    }
}