package com.creospace.recipe_kmp.presentation.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.creospace.recipe_kmp.data.local.FavoriteCats
import com.creospace.recipe_kmp.presentation.home.components.ErrorScreen
import com.creospace.recipe_kmp.presentation.home.components.LoadingScreen

@Composable
fun FavoriteScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    favoriteUiState: FavoriteUiState,
    retryAction: () -> Unit,
    toDetail: (FavoriteCats) -> Unit
) {
    when(favoriteUiState) {
        is FavoriteUiState.Error -> ErrorScreen(retryAction)
        is FavoriteUiState.Success ->
            FavoriteCatsList(
                FavoriteCatsList = favoriteUiState.favorite,
                navController = navController,
                toDetail = toDetail
            )
        is  FavoriteUiState.Loading -> LoadingScreen()
    }
}