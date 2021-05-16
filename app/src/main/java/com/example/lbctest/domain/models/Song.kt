package com.example.lbctest.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(
        val id: Int = 0,
        val albumId: Int = 0,
        val title: String = "",
        val url: String = "",
        val thumbnailUrl: String = ""
) : Parcelable