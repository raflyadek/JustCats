package com.creospace.recipe_kmp.data.repository

import com.creospace.recipe_kmp.data.local.FavoriteCats
import com.creospace.recipe_kmp.data.local.FavoriteDao
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.data.retrofit.CatApiService

//create an catsphotorepository but using an interface for easier maintain
//and then the class will be the under hood how this interface (display) works
interface CatsPhotosRepository {
    suspend fun getCatsPhotos(): List<Cats>
    suspend fun getCatsDetail(id: String): Cats
    suspend fun loadAllFavorite(): List<FavoriteCats>
    suspend fun insertFavorite(favoriteCats: FavoriteCats)
    suspend fun deleteFavorite(favoriteCats: FavoriteCats)
}

class DefaultCatsRepository(
    private val catsApiService: CatApiService,
    private val favoriteDao: FavoriteDao
) : CatsPhotosRepository {
    override suspend fun getCatsPhotos(): List<Cats> = catsApiService.getCatImages()
    override suspend fun getCatsDetail(id: String): Cats = catsApiService.getCatDetail(id)
    override suspend fun loadAllFavorite(): List<FavoriteCats> = favoriteDao.loadAll()
    override suspend fun insertFavorite(favoriteCats: FavoriteCats) = favoriteDao.insert(favoriteCats)
    override suspend fun deleteFavorite(favoriteCats: FavoriteCats) = favoriteDao.delete(favoriteCats)

}