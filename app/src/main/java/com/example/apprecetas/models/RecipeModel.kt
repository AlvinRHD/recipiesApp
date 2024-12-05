package com.example.apprecetas.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RecipeModel (
    val category: String = "",
    val id: String = "",
    val imageUrl: String = "",
    val ingredients: String = "",
    val time: Int = 0,
    val title: String = "",
    val youtubeUrl: String = ""
) : Parcelable




