package com.yasir.sculpture.arena.repository

import com.yasir.sculpture.arena.api.SculptureArenaResponse
import com.yasir.sculpture.arena.api.endpoint.PhotosEndpoint
import com.yasir.sculpture.arena.api.models.PhotoResponseModelItem
import com.yasir.sculpture.arena.api.models.SearchPhotosResponseModel
import com.yasir.sculpture.arena.api.safeApiCall

class PhotosRepository(private val api: PhotosEndpoint) {

    suspend fun getLatestPhotos(page: Int, orderBy: String = "latest"
    ): SculptureArenaResponse<List<PhotoResponseModelItem>> {
        return safeApiCall {
            api.fetchPhotos(
                page,
                PAGINATION_PAGE_SIZE,
                orderBy,
            )
        }
    }

    suspend fun getSearchPhotos(query: String,
                                pageNumber: Int
    ): SculptureArenaResponse<SearchPhotosResponseModel> {
        return safeApiCall {
            api.searchPhotos(
                query,
                pageNumber,
                PAGINATION_PAGE_SIZE,
            )
        }
    }



    companion object {
        private const val PAGINATION_PAGE_SIZE = 30
    }
}