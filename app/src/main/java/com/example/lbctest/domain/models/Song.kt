package com.example.lbctest.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(
        val id: Int,
        val albumId: Int,
        val title: String,
        val url: String,
        val thumbnailUrl: String,
) : Parcelable