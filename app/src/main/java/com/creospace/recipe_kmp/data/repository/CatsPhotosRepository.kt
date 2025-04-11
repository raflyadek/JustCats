package com.creospace.recipe_kmp.data.repository

import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.data.retrofit.CatApiService

//create an catsphotorepository but using an interface for easier maintain
//and then the class will be the under hood how this interface (display) works
interface CatsPhotosRepository {
    suspend fun getCatsPhotos(): List<Cats>
    suspend fun getCatsDetail(id: String): Cats
}

class DefaultCatsRepository(private val catsApiService: CatApiService) : CatsPhotosRepository {
    override suspend fun getCatsPhotos(): List<Cats> = catsApiService.getCatImages()

    override suspend fun getCatsDetail(id: String): Cats = catsApiService.getCatDetail(id)

}