package com.swag.boothmap.repository

import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.swag.boothmap.datacalsses.Booth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class Database {
    private val database = Firebase.database
    private val cityRef = database.getReference("Cities")

    suspend fun fetchCities(): List<String> = suspendCancellableCoroutine { continuation ->
        cityRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val cities = mutableListOf<String>()
                for (citySnapshot in snapshot.children) {
                    val cityName = citySnapshot.key ?: continue
                    cities.add(cityName)
                }
                continuation.resume(cities)
            }

            override fun onCancelled(error: DatabaseError) {
                continuation.resumeWithException(error.toException())
            }
        })
    }

    suspend fun fetchBooths(city: String): List<Booth> = suspendCancellableCoroutine { continuation ->
        cityRef.child(city).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val booths = mutableListOf<Booth>()
                    for (boothSnapshot in snapshot.children) {
                        val map = boothSnapshot.value as? Map<String, Any> ?: continue
                        val id = map["id"] as? String ?: ""
                        val name = map["name"] as? String ?: ""
                        val taluka = map["taluka"] as? String ?: ""
                        val bloname = map["bloname"] as? String ?: ""
                        val bloContact = map["bloContact"] as? String ?: ""
                        val district = map["district"] as? String ?: city
                        val latitude = (map["latitude"] as? Number)?.toDouble() ?: 0.0
                        val longitude = (map["longitude"] as? Number)?.toDouble() ?: 0.0

                        if (id.isNotEmpty() && bloname.isNotEmpty() &&
                            latitude != 0.0 && longitude != 0.0) {
                            booths.add(Booth(id = id, name = name, bloName = bloname, bloContact = bloContact, district = district, taluka = taluka, latitude = latitude, longitude = longitude))
                        }
                    }
                    continuation.resume(booths)
                } catch (e: Exception) {
                    continuation.resumeWithException(e)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                continuation.resumeWithException(error.toException())
            }
        })
    }


//    suspend fun fetchBooths2(city: String): List<Booth> = suspendCancellableCoroutine { continuation ->
//        cityRef.child(city).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                try {
//                    val booths = mutableListOf<Booth>()
//                    for (boothSnapshot in snapshot.children) {
//                        val id = boothSnapshot.child("id").getValue(String::class.java) ?: ""
//                        val bloname = boothSnapshot.child("bloname").getValue(String::class.java) ?: ""
//                        val bloContact = boothSnapshot.child("bloContact").getValue(String::class.java) ?: ""
//                        val district = boothSnapshot.child("district").getValue(String::class.java) ?: city
//                        val latitude = boothSnapshot.child("latitude").getValue(Double::class.java) ?: 0.0
//                        val longitude = boothSnapshot.child("longitude").getValue(Double::class.java) ?: 0.0
//
//                        if (id.isNotEmpty() && bloname.isNotEmpty() &&
//                            latitude != 0.0 && longitude != 0.0) {
//                            booths.add(Booth(id = id, bloName = bloname, bloContact = bloContact, district = district, latitude = latitude, longitude = longitude))
//                        }
//                    }
//                    continuation.resume(booths)
//                } catch (e: Exception) {
//                    continuation.resumeWithException(e)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                continuation.resumeWithException(error.toException())
//            }
//        })
//    }
}

