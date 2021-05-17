package com.example.lbctest.data.remote

import com.example.lbctest.data.remote.dto.SongDto
import retrofit2.http.GET

interface WebService {
    @GET("img/shared/technical-test.json")
    suspend fun getSongs(): List<SongDto>
}