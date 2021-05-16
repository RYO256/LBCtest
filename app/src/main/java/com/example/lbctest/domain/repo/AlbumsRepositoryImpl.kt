package com.example.lbctest.domain.repo

import com.example.lbctest.core.Resource
import com.example.lbctest.data.local.LocalDataSource
import com.example.lbctest.data.entities.SongEntity
import com.example.lbctest.data.entities.asSongEntity
import com.example.lbctest.data.remote.NetworkDataSource
import com.example.lbctest.domain.models.Album
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class AlbumsRepositoryImpl @Inject constructor(
        private val networkDataSource: NetworkDataSource,
        private val localDataSource: LocalDataSource
) : AlbumsRepository {

    override suspend fun getAlbums(): Flow<Resource<List<Album>>>  =
        callbackFlow {

            offer(getCachedAlbums())

            networkDataSource.getSongs().collect {
                when (it) {
                    is Resource.Success -> {
                        for (song in it.data) {
                            saveSong(song.asSongEntity())
                        }
                        offer(getCachedAlbums())
                    }
                    is Resource.Failure -> {
                        offer(getCachedAlbums())
                    }
                    else -> offer(getCachedAlbums())
                }
            }
        }

    override suspend fun getCachedAlbums(): Resource<List<Album>> =
        localDataSource.getCachedAlbums()


    override suspend fun saveSong(song: SongEntity) {
       localDataSource.saveSong(song)
    }

}