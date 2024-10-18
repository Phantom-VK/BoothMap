package com.swag.boothmap.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swag.boothmap.datacalsses.Booth
import com.swag.boothmap.repository.Database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LocationDataViewModel : ViewModel() {
    private val database = Database()
    private val _booths = MutableStateFlow<List<Booth>>(emptyList())
    val booths: StateFlow<List<Booth>> = _booths

    private val _selectedCity = MutableStateFlow<String?>(null)
    val selectedCity: StateFlow<String?> = _selectedCity

    private val _selectedTaluka = MutableStateFlow<String?>(null)
    val selectedTaluka: StateFlow<String?> = _selectedTaluka

    private val _selectedBooth = MutableStateFlow<String?>(null)
    val selectedBooth: StateFlow<String?> = _selectedBooth

    private val _uiState = MutableStateFlow<UiState>(UiState.LoadingCities)
    val uiState: StateFlow<UiState> = _uiState

    private var listOfCities: List<String> = emptyList()

    init {
        fetchCities()
    }

    private fun fetchCities() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.LoadingCities
                listOfCities = database.fetchCities()
                _uiState.value = UiState.CitiesLoaded
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Error fetching cities")
            }
        }
    }

    private fun fetchBooths(city: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.LoadingBooths
                _booths.value = database.fetchBooths(city)
                _uiState.value = UiState.BoothsLoaded
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Error fetching booths")
            }
        }
    }

    fun selectCity(city: String) {
        _selectedCity.value = city
        _selectedTaluka.value = null
        fetchBooths(city)
    }

    fun resetSelections() {
        _selectedCity.value = null
        _selectedTaluka.value = null
        _selectedBooth.value = null
        _booths.value = emptyList()
        _uiState.value = UiState.CitiesLoaded
    }

    fun selectTaluka(taluka: String?) {
        _selectedTaluka.value = taluka

        _booths.value = _booths.value.filter { it.taluka == taluka }
    }

    fun selectBooth(taluka: String?) {
        _selectedBooth.value = taluka
    }

    fun getListOfCities(): List<String> = listOfCities

    fun getListOfTalukas(): List<String> {
        return _booths.value.map { it.taluka }.distinct()
    }
    fun getListOfBooths(): List<String> {
        return _booths.value.map { it.name }.distinct()
    }
}

sealed class UiState {
    data object LoadingCities : UiState()
    data object CitiesLoaded : UiState()
    data object LoadingBooths : UiState()
    data object BoothsLoaded : UiState()
    data class Error(val message: String) : UiState()
}

