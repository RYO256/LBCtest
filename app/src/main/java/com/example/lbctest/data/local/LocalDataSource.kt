package com.example.lbctest.data.local

import com.example.lbctest.core.Resource
import com.example.lbctest.data.entities.SongEntity
import com.example.lbctest.data.entities.asAlbumList
import com.example.lbctest.data.entities.asSongsList
import com.example.lbctest.domain.models.Album
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LocalDataSource @Inject constructor(private val songsDao: SongsDao) {

    suspend fun saveSong(song: SongEntity) {
        songsDao.saveSong(song)
    }

    suspend fun getCachedAlbums(): Resource<List<Album>> {
        return Resource.Success(songsDao.getSongs().asSongsList().asAlbumList())
    }

    suspend fun removeCachedAlbums(){
        songsDao.deleteAllSongs()
    }

}