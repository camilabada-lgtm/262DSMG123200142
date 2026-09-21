package com.example.dessertclicker.model

import com.example.dessertclicker.data.Datasource

data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = Datasource.dessertList[currentDessertIndex].price,
    val currentDessertImageId: Int = Datasource.dessertList[currentDessertIndex].imageId
)