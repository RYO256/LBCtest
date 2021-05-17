package com.example.lbctest.data.local

import androidx.room.*
import com.example.lbctest.data.local.entities.SongEntity

@Dao
interface SongsDao {

    @Query("SELECT * FROM songsTable")
    suspend fun getAll(): List<SongEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(song: SongEntity)

    @Delete
    suspend fun delete(song: SongEntity)

    @Query("DELETE FROM songsTable")
    suspend fun deleteAll()
}