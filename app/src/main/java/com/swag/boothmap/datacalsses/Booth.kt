package com.swag.boothmap.datacalsses

import android.net.Uri

data class Booth(
    val name:String = "",
    val id:String = "",
    val latitude:Double,
    val longitude:Double,
    val district:String = "",
    val taluka:String = "",
    val bloName:String = "",
    val bloContact:String = "",
    val images:List<Uri> = emptyList()
)
