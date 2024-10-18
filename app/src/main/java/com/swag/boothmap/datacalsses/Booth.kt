package com.swag.boothmap.datacalsses


data class Booth(
    val name:String = "",
    val id:String = "",
    val latitude: Double = 0.0,
    val longitude:Double = 0.0,
    var district:String = "",
    val taluka:String = "",
    val bloName:String = "",
    val bloContact: String = "",
    val images:String = ""
)
