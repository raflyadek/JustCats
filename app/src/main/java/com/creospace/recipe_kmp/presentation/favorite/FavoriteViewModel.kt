package com.creospace.recipe_kmp.presentation.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creospace.recipe_kmp.data.local.FavoriteCats
import com.creospace.recipe_kmp.data.local.FavoriteRoomDatabase
import com.creospace.recipe_kmp.data.repository.CatsPhotosRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed class FavoriteUiState {
    data class Success(val favorite: List<FavoriteCats>) : FavoriteUiState()
    data object Loading : FavoriteUiState()
    data object Error : FavoriteUiState()
}

class FavoriteViewModel(
    private val catsPhotosRepository: CatsPhotosRepository,
) : ViewModel() {
    var favoriteUiState: FavoriteUiState by mutableStateOf(FavoriteUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            delay(5000)
            getAllFavorite()
        }
    }

    private fun getAllFavorite() {
        viewModelScope.launch {
            favoriteUiState = FavoriteUiState.Loading
            favoriteUiState = try {
                FavoriteUiState.Success(catsPhotosRepository.loadAllFavorite())
            } catch (e: IOException) {
                e.printStackTrace()
                FavoriteUiState.Error
            } catch (e: HttpException) {
                e.printStackTrace()
                FavoriteUiState.Error
            } catch (e: Exception) {
                e.printStackTrace()
                FavoriteUiState.Error
            }
        }
    }
}