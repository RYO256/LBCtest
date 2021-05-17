package com.example.lbctest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lbctest.domain.models.Album
import com.example.lbctest.domain.models.Song


@Entity(tableName = "songsTable")
data class SongEntity(
        @PrimaryKey
        val id: Int = 0,
        val albumId: Int = 0,
        val title: String = "",
        val url: String = "",
        val thumbnailUrl: String = ""
)

fun List<SongEntity>.asSongsList(): List<Song> = this.map {
        Song(it.id,it.albumId,it.title,it.url,it.thumbnailUrl)
}

fun List<Song>.asAlbumList(): List<Album> {
        val albums = mutableListOf<Album>()
        val group = this.groupBy { it.albumId }
        for (item in group) {
                val album = Album(item.key, item.value)
                albums.add(album)
        }

        return albums
}
