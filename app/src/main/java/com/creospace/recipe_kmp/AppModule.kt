package com.creospace.recipe_kmp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import com.creospace.recipe_kmp.data.local.FavoriteDao
import com.creospace.recipe_kmp.data.local.FavoriteRoomDatabase
//import com.creospace.recipe_kmp.data.local.FavoriteDao
//import com.creospace.recipe_kmp.data.local.FavoriteRoomDatabase
import com.creospace.recipe_kmp.data.repository.CatsPhotosRepository
import com.creospace.recipe_kmp.data.repository.DefaultCatsRepository
import com.creospace.recipe_kmp.data.retrofit.CatApiService
import com.creospace.recipe_kmp.presentation.detail.DetailViewModel
import com.creospace.recipe_kmp.presentation.favorite.FavoriteViewModel
import com.creospace.recipe_kmp.presentation.home.HomeViewModel
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
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

    single { provideDatabase(get()) }
    single { provideDao(get()) }
    single { provideCoilCache(get()) }


    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::FavoriteViewModel)
}


fun provideCoilCache(application: Application) {
    ImageLoader.Builder(application)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(application, 0.25)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(application.cacheDir.resolve("image_cache"))
                .maxSizePercent(0.02)
                .build()
        }
        .build()
}
fun provideRetrofit(): Retrofit {
    val json = Json { ignoreUnknownKeys = true }
    val apiKey = BuildConfig.API_KEY
    val BASE_URL =
        "https://api.thecatapi.com/"
    val apiKeyInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", apiKey)
            .build()
        chain.proceed(request)
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()
    return Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()
}

fun provideDatabase(application: Application): FavoriteRoomDatabase =
    Room.databaseBuilder(
        application,
        FavoriteRoomDatabase::class.java,
        "favorite"
    ).fallbackToDestructiveMigration().addMigrations().build()

fun provideDao(favoriteRoomDatabase: FavoriteRoomDatabase): FavoriteDao =
    favoriteRoomDatabase.getFavoriteDao()

