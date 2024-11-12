package com.example.lab8_api.network
import com.example.lab8_api.data.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    suspend fun getPhotos(@Query("client_id") clientId: String): List<Photo>
}