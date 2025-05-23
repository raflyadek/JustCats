package com.creospace.recipe_kmp.data.retrofit

import com.creospace.recipe_kmp.data.model.Cats
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CatApiService {

    @GET("v1/images/search?limit=20&has_breeds=1")
    suspend fun getCatImages(
//        @Query("limit") limit: Int,
//        @Query("page") page: Int,
//        @Query("has_breeds") has_breeds: Int
    ): List<Cats>

    @GET("v1/images/{id}")
    suspend fun getCatDetail(
        @Path("id") id: String
    ): Cats
}