package com.example.lbctest.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lbctest.domain.models.Album
import com.example.lbctest.domain.models.Song
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SongDto(
        @SerializedName("albumId")
        val albumId: Int = 0,
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("title")
        val title: String = "",
        @SerializedName("url")
        val url: String = "",
        @SerializedName("thumbnailUrl")
        val thumbnailUrl: String = ""
) : Parcelable

@Entity(tableName = "songsTable")
data class SongEntity(
        @PrimaryKey
        val albumId: Int = 0,
        val title: String = "",
        val url: String = "",
        val thumbnailUrl: String = ""
)

fun List<SongEntity>.asSongsList(): List<Song> = this.map {
        Song(it.albumId,it.title,it.url,it.thumbnailUrl)
}

fun List<Song>.asAlbumList(): List<Album>  {
        val albums = mutableListOf<Album>()
        var album = Album(this)
        albums.add(album)
        return albums
}

fun SongDto.asSongEntity(): SongEntity = SongEntity(this.albumId,this.title,this.url,this.thumbnailUrl)

fun SongEntity.asSong(): Song = Song(this.albumId,this.title,this.url,this.thumbnailUrl)