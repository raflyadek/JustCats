package com.creospace.recipe_kmp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.data.repository.CatsPhotosRepository

class CatSource(repository: CatsPhotosRepository): PagingSource<String, Cats>() {
    override fun getRefreshKey(state: PagingState<String, Cats>): String? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Cats> {
        TODO("Not yet implemented")
    }

}