package com.example.lbctest.data.remote

import com.example.lbctest.core.Resource
import com.example.lbctest.data.remote.dto.SongDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(
        private val webService: WebService
) {
    fun getSongs(): Flow<Resource<List<SongDto>>> =
            callbackFlow {
                offer(
                        try {
                            Resource.Success(
                                    webService.getSongs()
                            )
                        } catch (e: Exception) {
                            Resource.Failure(e)
                        }
                )
                awaitClose { close() }
            }
}