package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Place
import com.example.mycity.model.PlaceCategory

object LocalPlacesDataProvider {
    val allPlaces = listOf(
        Place(1, R.string.cafe_bisetti, R.string.cafe_bisetti_desc, R.string.cafe_bisetti_addr, R.drawable.imagen_cafe, PlaceCategory.CAFES),
        Place(2, R.string.cafe_verde, R.string.cafe_verde_desc, R.string.cafe_verde_addr, R.drawable.imagen_matcha, PlaceCategory.CAFES),
        Place(3, R.string.rest_central, R.string.rest_central_desc, R.string.rest_central_addr, R.drawable.imagen_restaurante_central, PlaceCategory.RESTAURANTS),
        Place(4, R.string.rest_isolina, R.string.rest_isolina_desc, R.string.rest_isolina_addr, R.drawable.imagen_restaurante_astridygaston, PlaceCategory.RESTAURANTS),
        Place(5, R.string.park_kennedy, R.string.park_kennedy_desc, R.string.park_kennedy_addr, R.drawable.imagen_parquekennedy, PlaceCategory.PARKS),
        Place(6, R.string.park_olivar, R.string.park_olivar_desc, R.string.park_olivar_addr, R.drawable.imagen_parqueelolivar, PlaceCategory.PARKS),
        Place(7, R.string.museum_mali, R.string.museum_mali_desc, R.string.museum_mali_addr, R.drawable.placeholder, PlaceCategory.MUSEUMS),
        Place(8, R.string.museum_larco, R.string.museum_larco_desc, R.string.museum_larco_addr, R.drawable.placeholder, PlaceCategory.MUSEUMS),
    )

    fun getPlacesByCategory(category: PlaceCategory) = allPlaces.filter { it.category == category }

    val defaultPlace = allPlaces.first()
}