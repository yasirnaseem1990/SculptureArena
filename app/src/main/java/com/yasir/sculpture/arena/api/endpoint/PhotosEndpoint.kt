package com.yasir.sculpture.arena.api.endpoint

import com.yasir.sculpture.arena.api.models.PhotoResponseModelItem
import com.yasir.sculpture.arena.api.models.SearchPhotosResponseModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PhotosEndpoint {

    @GET("photos")
    suspend fun fetchPhotos(
        @Query("page") page: Int? = 1,
        @Query("per_page") numOfPhotos: Int? = 10,
        @Query("order_by") orderBy: String? = "latest"
    ): List<PhotoResponseModelItem>

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") numOfPhotos: Int = 10,
    ): SearchPhotosResponseModel
}