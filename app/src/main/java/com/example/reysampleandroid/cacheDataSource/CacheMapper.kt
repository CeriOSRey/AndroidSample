package com.example.reysampleandroid.cacheDataSource

import com.example.reysampleandroid.model.Joke
import com.example.reysampleandroid.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): EntityMapper<JokeCacheEntity, Joke>
{
    override fun mapFromEntity(entity: JokeCacheEntity): Joke {
        return Joke(
            id = entity.id,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Joke): JokeCacheEntity {
        return JokeCacheEntity(
            id = domainModel.id,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities: List<JokeCacheEntity>): List<Joke> {
        return entities.map { mapFromEntity(it) }
    }

}