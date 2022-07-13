package com.example.reysampleandroid.networkDataSource

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JokeNetworkEntity(
    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("joke")
    @Expose
    var body: String
)
