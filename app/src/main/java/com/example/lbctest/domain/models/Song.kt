package com.example.lbctest.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(
        @SerializedName("albumId")
        val albumId: Int = 0,
        @SerializedName("title")
        val title: String = "",
        @SerializedName("url")
        val url: String = "",
        @SerializedName("thumbnailUrl")
        val thumbnailUrl: String = ""
) : Parcelable