package com.creospace.recipe_kmp.presentation.detail

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.creospace.recipe_kmp.CatsApplication
import com.creospace.recipe_kmp.data.local.FavoriteCats
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.data.repository.CatsPhotosRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed class DetailUiState {
    data class Success(val detail: Cats) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(
    private val catsPhotosRepository: CatsPhotosRepository,
    private val id: String
) : ViewModel() {
    var detailUiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    init {
        getCatDetail(id)
    }

    private fun getCatDetail(id: String) {
        viewModelScope.launch {
            detailUiState = DetailUiState.Loading
            detailUiState = try {
                DetailUiState.Success(catsPhotosRepository.getCatsDetail(id))
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun insertFavorite(favoriteCats: FavoriteCats) {
        viewModelScope.launch {
            catsPhotosRepository.insertFavorite(favoriteCats)
        }
    }

    fun deleteFavorite(favoriteCats: FavoriteCats) {
        viewModelScope.launch {
            catsPhotosRepository.deleteFavorite(favoriteCats)
        }
    }
}