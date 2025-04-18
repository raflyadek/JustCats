package com.creospace.recipe_kmp

import com.creospace.recipe_kmp.data.repository.CatsPhotosRepository
import com.creospace.recipe_kmp.data.repository.DefaultCatsRepository
import com.creospace.recipe_kmp.data.retrofit.CatApiService
import com.creospace.recipe_kmp.presentation.detail.DetailViewModel
import com.creospace.recipe_kmp.presentation.home.HomeViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val appModules = module {
    factoryOf(::DefaultCatsRepository) bind CatsPhotosRepository::class
    single<CatApiService> {
        get<Retrofit>().create(CatApiService::class.java)
    }
    single { provideRetrofit() }

    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)

}


fun provideRetrofit(): Retrofit {
    val json = Json { ignoreUnknownKeys = true }
    val BASE_URL =
        "https://api.thecatapi.com/"
    return Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()
}

