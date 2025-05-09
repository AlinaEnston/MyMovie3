package com.larina.mymovie

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Film(
    val title: String,
    val poster: Int,
    val description: String,
    var isInFavorites: Boolean = false
) : Parcelable


