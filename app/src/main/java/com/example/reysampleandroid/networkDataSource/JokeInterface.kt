package com.example.reysampleandroid.networkDataSource

import retrofit2.http.GET

interface JokeInterface {
    @GET("")
    suspend fun getJoke(): List<JokeNetworkEntity>
}