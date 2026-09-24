package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.LocalPlacesDataProvider
import com.example.mycity.model.Place
import com.example.mycity.model.PlaceCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MyCityUiState(
    val allPlaces: List<Place> = LocalPlacesDataProvider.allPlaces,
    val currentCategory: PlaceCategory? = null,
    val currentPlace: Place? = null
) {
    val placesInCategory: List<Place>
        get() = allPlaces.filter { it.category == currentCategory }
}

class MyCityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    fun onCategorySelected(category: PlaceCategory) {
        _uiState.update { it.copy(currentCategory = category, currentPlace = null) }
    }

    fun onPlaceSelected(place: Place) {
        _uiState.update { it.copy(currentPlace = place) }
    }

    fun onBackFromDetail() {
        _uiState.update { it.copy(currentPlace = null) }
    }

    fun onBackFromList() {
        _uiState.update { it.copy(currentCategory = null, currentPlace = null) }
    }
}