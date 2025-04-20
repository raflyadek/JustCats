package com.creospace.recipe_kmp.presentation.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creospace.recipe_kmp.data.local.FavoriteCats
import com.creospace.recipe_kmp.data.local.FavoriteRoomDatabase
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed class FavoriteUiState {
    data class Success(val favorite: List<FavoriteCats>) : FavoriteUiState()
    data object Loading : FavoriteUiState()
    data object Error : FavoriteUiState()
}

class FavoriteViewModel (
    private val favoriteRoomDatabase: FavoriteRoomDatabase,

) : ViewModel() {
    var favoriteUiState: FavoriteUiState by mutableStateOf(FavoriteUiState.Loading)
        private set

    init {
        getAllFavorite()
    }

    private fun getAllFavorite() {
        viewModelScope.launch {
            favoriteUiState = FavoriteUiState.Loading
            favoriteUiState = try {
                FavoriteUiState.Success(favoriteRoomDatabase.favoriteDao().getAll())
            } catch (e: IOException) {
                FavoriteUiState.Error
            } catch (e: HttpException) {
                FavoriteUiState.Error
            }
        }
    }
}