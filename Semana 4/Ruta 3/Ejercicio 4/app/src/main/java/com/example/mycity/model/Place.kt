package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val id: Int,
    @StringRes val nameResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @StringRes val addressResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    val category: PlaceCategory
)

enum class PlaceCategory {
    CAFES, RESTAURANTS, PARKS, MUSEUMS
}