package com.example.lbctest.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album (val id : Int ,val songs : List<Song>) : Parcelable