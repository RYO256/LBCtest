package com.example.lbctest.data.remote

import com.example.lbctest.core.Resource
import com.example.lbctest.data.entities.SongDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getSongs(): Flow<Resource<List<SongDto>>> =
        callbackFlow {
            offer(
                Resource.Success(
                    webService.getSongs() ?: listOf()
                )
            )
            awaitClose { close() }
        }
}