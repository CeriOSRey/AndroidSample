package com.example.reysampleandroid.networkDataSource

import retrofit2.http.GET

interface JokeNetworkInterface {
    @GET("")
    suspend fun getJokes(): List<JokeNetworkEntity>
    suspend fun getJoke(): JokeNetworkEntity
}