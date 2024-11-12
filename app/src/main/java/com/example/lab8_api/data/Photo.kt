package com.example.lab8_api.data
import com.squareup.moshi.Json

data class Photo(
    val id: String,
    val description: String?,
    val urls: Urls
)