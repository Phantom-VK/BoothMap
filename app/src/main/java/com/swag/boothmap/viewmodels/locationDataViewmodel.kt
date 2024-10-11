package com.swag.boothmap.viewmodels

import androidx.lifecycle.ViewModel
import com.swag.boothmap.datacalsses.Booth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LocationDataViewModel : ViewModel() {
    private val _booths = MutableStateFlow<List<Booth>>(emptyList())
    val booths: StateFlow<List<Booth>> = _booths

    private val _selectedCity = MutableStateFlow<String?>(null)
    val selectedCity: StateFlow<String?> = _selectedCity



    private val _selectedTaluka = MutableStateFlow<String?>(null)
    val selectedTaluka: StateFlow<String?> = _selectedTaluka

    val listOfBooths = mapOf(
        "Parbhani" to listOf(
            Booth(name = "Parbhani Central Booth", id = "PRB001", latitude = 19.2645, longitude = 76.7816, district = "Parbhani", taluka = "Parbhani City", bloName = "John Doe", bloContact = "1234567890"),
            Booth(name = "Shivaji Nagar Booth", id = "PRB002", latitude = 19.2687, longitude = 76.7757, district = "Parbhani", taluka = "Parbhani City", bloName = "Jane Smith", bloContact = "2345678901"),
            Booth(name = "Jintur Road Booth", id = "PRB003", latitude = 19.2601, longitude = 76.7868, district = "Parbhani", taluka = "Jintur", bloName = "Alice Johnson", bloContact = "3456789012"),
            Booth(name = "Gangakhed Road Booth", id = "PRB004", latitude = 19.2712, longitude = 76.7702, district = "Parbhani", taluka = "Gangakhed", bloName = "Bob Williams", bloContact = "4567890123"),
            Booth(name = "Pathri Road Booth", id = "PRB005", latitude = 19.2589, longitude = 76.7738, district = "Parbhani", taluka = "Pathri", bloName = "Charlie Brown", bloContact = "5678901234"),
            Booth(name = "MIDC Parbhani Booth", id = "PRB006", latitude = 19.2734, longitude = 76.7812, district = "Parbhani", taluka = "Parbhani City", bloName = "David Miller", bloContact = "6789012345"),
            Booth(name = "Vasmat Road Booth", id = "PRB007", latitude = 19.2623, longitude = 76.7929, district = "Parbhani", taluka = "Vasmat", bloName = "Eva Davis", bloContact = "7890123456")
        ),
        "Nanded" to listOf(
            Booth(name = "Nanded Central Booth", id = "NND001", latitude = 19.1383, longitude = 77.3210, district = "Nanded", taluka = "Nanded City", bloName = "Frank White", bloContact = "8901234567"),
            Booth(name = "Vazirabad Booth", id = "NND002", latitude = 19.1450, longitude = 77.3150, district = "Nanded", taluka = "Vazirabad", bloName = "Grace Lee", bloContact = "9012345678"),
            Booth(name = "Shivaji Nagar Booth", id = "NND003", latitude = 19.1320, longitude = 77.3280, district = "Nanded", taluka = "Nanded City", bloName = "Henry Taylor", bloContact = "0123456789"),
            Booth(name = "Degloor Naka Booth", id = "NND004", latitude = 19.1410, longitude = 77.3320, district = "Nanded", taluka = "Degloor", bloName = "Ivy Chen", bloContact = "1234567890"),
            Booth(name = "Taroda Booth", id = "NND005", latitude = 19.1350, longitude = 77.3100, district = "Nanded", taluka = "Taroda", bloName = "Jack Wilson", bloContact = "2345678901"),
            Booth(name = "MIDC Booth", id = "NND006", latitude = 19.1480, longitude = 77.3250, district = "Nanded", taluka = "Nanded City", bloName = "Karen Martinez", bloContact = "3456789012"),
            Booth(name = "Asarjan Booth", id = "NND007", latitude = 19.1300, longitude = 77.3180, district = "Nanded", taluka = "Asarjan", bloName = "Leo Anderson", bloContact = "4567890123")
        ),
        "Aurangabad" to listOf(
            Booth(name = "Aurangabad Central Booth", id = "AUR001", latitude = 19.8762, longitude = 75.3433, district = "Aurangabad", taluka = "Aurangabad City", bloName = "Mia Jackson", bloContact = "5678901234"),
            Booth(name = "CIDCO Booth", id = "AUR002", latitude = 19.8705, longitude = 75.3203, district = "Aurangabad", taluka = "CIDCO", bloName = "Noah Thompson", bloContact = "6789012345"),
            Booth(name = "Gulmandi Booth", id = "AUR003", latitude = 19.8957, longitude = 75.3202, district = "Aurangabad", taluka = "Gulmandi", bloName = "Olivia Garcia", bloContact = "7890123456"),
            Booth(name = "Hudco Booth", id = "AUR004", latitude = 19.8684, longitude = 75.3644, district = "Aurangabad", taluka = "Hudco", bloName = "Peter Kim", bloContact = "8901234567"),
            Booth(name = "Jalna Road Booth", id = "AUR005", latitude = 19.8816, longitude = 75.3594, district = "Aurangabad", taluka = "Jalna Road", bloName = "Quinn Brown", bloContact = "9012345678"),
            Booth(name = "Beed Bypass Booth", id = "AUR006", latitude = 19.8896, longitude = 75.3703, district = "Aurangabad", taluka = "Beed Bypass", bloName = "Rachel Lee", bloContact = "0123456789"),
            Booth(name = "Garkheda Booth", id = "AUR007", latitude = 19.8605, longitude = 75.3203, district = "Aurangabad", taluka = "Garkheda", bloName = "Sam Davis", bloContact = "1234567890")
        ),
        "Latur" to listOf(
            Booth(name = "Latur Central Booth", id = "LTR001", latitude = 18.4088, longitude = 76.5604, district = "Latur", taluka = "Latur City", bloName = "Tina Wilson", bloContact = "2345678901"),
            Booth(name = "MIDC Latur Booth", id = "LTR002", latitude = 18.4031, longitude = 76.5684, district = "Latur", taluka = "MIDC", bloName = "Uma Patel", bloContact = "3456789012"),
            Booth(name = "Ausa Road Booth", id = "LTR003", latitude = 18.4160, longitude = 76.5479, district = "Latur", taluka = "Ausa Road", bloName = "Victor Martinez", bloContact = "4567890123"),
            Booth(name = "Ganjgolai Booth", id = "LTR004", latitude = 18.3967, longitude = 76.5457, district = "Latur", taluka = "Ganjgolai", bloName = "Wendy Taylor", bloContact = "5678901234"),
            Booth(name = "Shivaji Nagar Booth", id = "LTR005", latitude = 18.4141, longitude = 76.5744, district = "Latur", taluka = "Shivaji Nagar", bloName = "Xavier Chen", bloContact = "6789012345"),
            Booth(name = "Barshi Road Booth", id = "LTR006", latitude = 18.3935, longitude = 76.5811, district = "Latur", taluka = "Barshi Road", bloName = "Yara Johnson", bloContact = "7890123456"),
            Booth(name = "Gandhi Chowk Booth", id = "LTR007", latitude = 18.4208, longitude = 76.5534, district = "Latur", taluka = "Gandhi Chowk", bloName = "Zack Anderson", bloContact = "8901234567")
        )
    )

    val listOfCities = listOfBooths.keys.toList()



    fun selectCity(city: String) {
        _selectedCity.value = city
        _selectedTaluka.value = null
        _booths.value = listOfBooths[city] ?: emptyList()
    }

    fun selectTaluka(taluka: String) {
        _selectedTaluka.value = taluka
        _booths.value = listOfBooths[_selectedCity.value]?.filter { it.taluka == taluka } ?: emptyList()
    }

    fun clearSelection() {
        _selectedCity.value = null
        _selectedTaluka.value = null
        _booths.value = emptyList()
    }

    fun getListOfTalukas(): List<String> {
        return listOfBooths[_selectedCity.value]?.map { it.taluka } ?: emptyList()
    }
}