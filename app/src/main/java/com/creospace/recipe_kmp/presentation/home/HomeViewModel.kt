package com.creospace.recipe_kmp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil3.network.HttpException
import com.creospace.recipe_kmp.CatsApplication
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.data.repository.CatsPhotosRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed interface HomeUiState {
    data class Success(val cats: List<Cats>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

class HomeViewModel(private val catsPhotosRepository: CatsPhotosRepository) : ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getCatPhoto()
    }

    private fun getCatPhoto() {
        viewModelScope.launch {
            homeUiState = HomeUiState.Loading
            homeUiState = try {
                HomeUiState.Success(catsPhotosRepository.getCatsPhotos())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val applicaton = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as CatsApplication)
                val catsPhotosRepository = applicaton.container.catsPhotosRepository
                HomeViewModel(catsPhotosRepository = catsPhotosRepository)
            }
        }
    }
}
