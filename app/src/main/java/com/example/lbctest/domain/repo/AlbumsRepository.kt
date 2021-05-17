package com.example.lbctest.domain.repo

import com.example.lbctest.core.Resource
import com.example.lbctest.domain.models.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun getAll(): Flow<Resource<List<Album>>>
}