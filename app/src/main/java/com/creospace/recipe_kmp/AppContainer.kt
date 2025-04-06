package com.creospace.recipe_kmp

import com.creospace.recipe_kmp.data.repository.CatsPhotosRepository
import com.creospace.recipe_kmp.data.repository.DefaultCatsRepository
import com.creospace.recipe_kmp.data.retrofit.CatApiService
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonIgnoreUnknownKeys
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

interface AppContainer {
    val catsPhotosRepository: CatsPhotosRepository
}

class DefaultAppContainer: AppContainer {
    val json = Json { ignoreUnknownKeys = true }
    private val BASE_URL =
        "https://api.thecatapi.com/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: CatApiService by lazy {
            retrofit.create(CatApiService::class.java)
        }

    override val catsPhotosRepository: CatsPhotosRepository by lazy {
        DefaultCatsRepository(retrofitService)
    }
}

