package com.example.reysampleandroid.networkDataSource

import retrofit2.http.GET
import retrofit2.http.Headers

interface JokeNetworkInterface {

    @Headers("Content-Type: application/json")
    @GET("/")
    suspend fun getJokes(): List<JokeNetworkEntity>

    @Headers("Content-Type: application/json")
    @GET("/")
    suspend fun getJoke(): JokeNetworkEntity
}