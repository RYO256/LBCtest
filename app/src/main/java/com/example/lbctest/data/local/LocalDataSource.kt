package com.example.lbctest.data.local

import com.example.lbctest.core.Resource
import com.example.lbctest.data.local.entities.SongEntity
import com.example.lbctest.data.local.entities.asAlbumList
import com.example.lbctest.data.local.entities.asSongsList
import com.example.lbctest.domain.models.Album
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LocalDataSource @Inject constructor(private val songsDao: SongsDao) {

    suspend fun saveSong(song: SongEntity) =
            songsDao.save(song)

    suspend fun getCachedAlbums(): Resource<List<Album>> =
            Resource.Success(songsDao.getAll().asSongsList().asAlbumList())

    suspend fun removeCachedAlbums() = songsDao.deleteAll()

}