package com.example.reysampleandroid.networkDataSource

import com.example.reysampleandroid.model.Joke
import com.example.reysampleandroid.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<JokeNetworkEntity, Joke>
{
    override fun mapFromEntity(entity: JokeNetworkEntity): Joke {
        return Joke(
            id = entity.id,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Joke): JokeNetworkEntity {
        return JokeNetworkEntity(
            id = domainModel.id,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities: List<JokeNetworkEntity>): List<Joke> {
        return entities.map { mapFromEntity(it) }
    }
}