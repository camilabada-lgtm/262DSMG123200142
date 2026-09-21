package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.DessertUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun updateRevenue(index: Int = _uiState.value.currentDessertIndex) {
        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue + Datasource.dessertList[index].price
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (dessert in Datasource.dessertList) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertIndex = Datasource.dessertList.indexOf(dessert)
            } else {
                break
            }
        }
        return dessertIndex
    }

    private fun updateGameState(dessertsSold: Int) {
        val dessertIndex = determineDessertIndex(dessertsSold)
        _uiState.update { currentState ->
            currentState.copy(
                dessertsSold = dessertsSold,
                currentDessertIndex = dessertIndex,
                currentDessertPrice = Datasource.dessertList[dessertIndex].price,
                currentDessertImageId = Datasource.dessertList[dessertIndex].imageId
            )
        }
    }

    fun onDessertClicked() {
        updateRevenue()

        val dessertsSold = uiState.value.dessertsSold + 1
        updateGameState(dessertsSold)
    }
}