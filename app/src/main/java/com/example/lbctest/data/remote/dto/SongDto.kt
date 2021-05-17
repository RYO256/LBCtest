package com.example.lbctest.data.remote.dto

import android.os.Parcelable
import com.example.lbctest.data.local.entities.SongEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SongDto(
        @SerializedName("albumId")
        val albumId: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("thumbnailUrl")
        val thumbnailUrl: String?
) : Parcelable

fun SongDto.asSongEntity(): SongEntity = SongEntity(this.id ?: 0, this.albumId ?: 0, this.title
        ?: "", this.url ?: "", this.thumbnailUrl ?: "")
