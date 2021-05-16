package com.example.lbctest.data.local

import androidx.room.*
import com.example.lbctest.data.entities.SongEntity

@Dao
interface SongsDao {

    @Query("SELECT * FROM songsTable") // This Like operator is needed due that the API returns blank spaces in the name
    suspend fun getSongs(): List<SongEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSong(song: SongEntity)

    @Delete
    suspend fun deleteSong(song: SongEntity)

}