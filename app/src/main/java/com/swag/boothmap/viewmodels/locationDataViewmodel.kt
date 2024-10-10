package com.swag.boothmap.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.swag.boothmap.screens.MapLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocationDataViewModel : ViewModel() {
    private val _locations = MutableStateFlow<List<MapLocation>>(emptyList())
    val locations: StateFlow<List<MapLocation>> = _locations

    private val _selectedCity = MutableStateFlow<String?>("Nanded")
    val selectedCity: StateFlow<String?> = _selectedCity

    private val _listOfCities = MutableStateFlow(mapOf(
        "Parbhani" to listOf(
            MapLocation(LatLng(19.2645, 76.7816), "Parbhani Central Booth", "Municipal Corporation Office", Color.Red),
            MapLocation(LatLng(19.2687, 76.7757), "Shivaji Nagar Booth", "Shivaji Nagar Community Hall", Color.Blue),
            MapLocation(LatLng(19.2601, 76.7868), "Jintur Road Booth", "Jintur Road High School", Color.Green),
            MapLocation(LatLng(19.2712, 76.7702), "Gangakhed Road Booth", "Gangakhed Road Primary School", Color.Yellow),
            MapLocation(LatLng(19.2589, 76.7738), "Pathri Road Booth", "Pathri Road College", Color(0xFF8B00FF)),
            MapLocation(LatLng(19.2734, 76.7812), "MIDC Parbhani Booth", "MIDC Office Complex", Color(0xFFFFA500)),
            MapLocation(LatLng(19.2623, 76.7929), "Vasmat Road Booth", "Vasmat Road Community Center", Color(0xFF00FFFF))
        ),
        "Nanded" to listOf(
            MapLocation(LatLng(19.1383, 77.3210), "Nanded Central Booth", "Main city voting center", Color.Red),
            MapLocation(LatLng(19.1450, 77.3150), "Vazirabad Booth", "Near Vazirabad Police Station", Color.Blue),
            MapLocation(LatLng(19.1320, 77.3280), "Shivaji Nagar Booth", "Community Hall", Color.Green),
            MapLocation(LatLng(19.1410, 77.3320), "Degloor Naka Booth", "Near Bus Stand", Color.Yellow),
            MapLocation(LatLng(19.1350, 77.3100), "Taroda Booth", "Taroda Government School", Color(0xFF8B00FF)),
            MapLocation(LatLng(19.1480, 77.3250), "MIDC Booth", "MIDC Area Community Center", Color(0xFFFFA500)),
            MapLocation(LatLng(19.1300, 77.3180), "Asarjan Booth", "Asarjan Library Hall", Color(0xFF00FFFF))
        ),
        "Aurangabad" to listOf(
            MapLocation(LatLng(19.8762, 75.3433), "Aurangabad Central Booth", "City Hall", Color.Red),
            MapLocation(LatLng(19.8705, 75.3203), "CIDCO Booth", "CIDCO Office Complex", Color.Blue),
            MapLocation(LatLng(19.8957, 75.3202), "Gulmandi Booth", "Gulmandi Community Center", Color.Green),
            MapLocation(LatLng(19.8684, 75.3644), "Hudco Booth", "Hudco Shopping Complex", Color.Yellow),
            MapLocation(LatLng(19.8816, 75.3594), "Jalna Road Booth", "Jalna Road Civic Center", Color(0xFF8B00FF)),
            MapLocation(LatLng(19.8896, 75.3703), "Beed Bypass Booth", "Beed Bypass Community Hall", Color(0xFFFFA500)),
            MapLocation(LatLng(19.8605, 75.3203), "Garkheda Booth", "Garkheda Municipal School", Color(0xFF00FFFF))
        ),
        "Latur" to listOf(
            MapLocation(LatLng(18.4088, 76.5604), "Latur Central Booth", "Municipal Corporation Building", Color.Red),
            MapLocation(LatLng(18.4031, 76.5684), "MIDC Latur Booth", "MIDC Office", Color.Blue),
            MapLocation(LatLng(18.4160, 76.5479), "Ausa Road Booth", "Ausa Road Community Hall", Color.Green),
            MapLocation(LatLng(18.3967, 76.5457), "Ganjgolai Booth", "Ganjgolai Public School", Color.Yellow),
            MapLocation(LatLng(18.4141, 76.5744), "Shivaji Nagar Booth", "Shivaji Nagar Park", Color(0xFF8B00FF)),
            MapLocation(LatLng(18.3935, 76.5811), "Barshi Road Booth", "Barshi Road Police Station", Color(0xFFFFA500)),
            MapLocation(LatLng(18.4208, 76.5534), "Gandhi Chowk Booth", "Gandhi Chowk Library", Color(0xFF00FFFF))
        )
    ))
    val listOfCities: StateFlow<Map<String, List<MapLocation>>> = _listOfCities

    fun selectCity(city: String) {
        _selectedCity.value = city
        _locations.value = _listOfCities.value[city] ?: emptyList()
    }

    fun clearSelection() {
        _selectedCity.value = null
        _locations.value = emptyList()
    }
}