package com.example.lbctest.domain.usecases

import com.example.lbctest.core.Resource
import com.example.lbctest.domain.models.Album
import com.example.lbctest.domain.repo.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
        private val repository: AlbumsRepository,
) {
    suspend operator fun invoke(): Flow<Resource<List<Album>>> = repository.getAlbums()

}