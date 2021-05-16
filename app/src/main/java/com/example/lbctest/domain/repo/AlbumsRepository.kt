package com.example.lbctest.domain.repo

import com.example.lbctest.core.Resource
import com.example.lbctest.data.entities.SongEntity
import com.example.lbctest.domain.models.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    suspend fun getAlbums(): Flow<Resource<List<Album>>>
    suspend fun getCachedAlbums(): Resource<List<Album>>
    suspend fun saveSong(song: SongEntity)
}