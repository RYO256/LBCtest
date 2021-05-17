package com.example.lbctest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lbctest.data.local.entities.SongEntity

@Database(entities = [SongEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songsDao(): SongsDao
}