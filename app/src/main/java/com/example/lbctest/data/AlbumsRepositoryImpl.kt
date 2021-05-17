package com.example.lbctest.data

import com.example.lbctest.core.Resource
import com.example.lbctest.data.local.LocalDataSource
import com.example.lbctest.data.remote.NetworkDataSource
import com.example.lbctest.data.remote.dto.asSongEntity
import com.example.lbctest.domain.models.Album
import com.example.lbctest.domain.repo.AlbumsRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class AlbumsRepositoryImpl @Inject constructor(
        private val networkDataSource: NetworkDataSource,
        private val localDataSource: LocalDataSource
) : AlbumsRepository {

    override fun getAll(): Flow<Resource<List<Album>>> =
            flow {
                emit(localDataSource.getCachedAlbums())

                networkDataSource.getSongs().collect {
                    when (it) {
                        is Resource.Success -> {
                            localDataSource.removeCachedAlbums()
                            for (song in it.data) {
                                localDataSource.saveSong(song.asSongEntity())
                            }
                            emit(localDataSource.getCachedAlbums())
                        }

                        else -> emit(localDataSource.getCachedAlbums())
                    }
                }
            }
}