package com.creospace.recipe_kmp

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.creospace.recipe_kmp.data.local.FavoriteCats
//import com.creospace.recipe_kmp.data.local.FavoriteCats
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.presentation.detail.DetailScreen
import com.creospace.recipe_kmp.presentation.detail.DetailUiState
import com.creospace.recipe_kmp.presentation.detail.DetailViewModel
import com.creospace.recipe_kmp.presentation.favorite.FavoriteScreen
import com.creospace.recipe_kmp.presentation.favorite.FavoriteViewModel
import com.creospace.recipe_kmp.presentation.home.HomeScreen
import com.creospace.recipe_kmp.presentation.home.HomeViewModel
import com.creospace.recipe_kmp.presentation.profile.ProfileScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import androidx.lifecycle.viewmodel.compose.viewModel as viewModel1


@Composable
fun MainNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            val homeViewModel = koinViewModel<HomeViewModel>()
            val navigateToDetail: (Cats) -> Unit = { cat ->
                navController.navigate("DetailScreen/${cat.id}")
            }
            HomeScreen(
                homeUiState = homeViewModel.homeUiState,
                navController = navController,
                paddingValues = paddingValues,
                retryAction = {},
                toDetail = navigateToDetail
            )
        }
        composable(
            route = "DetailScreen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("id") ?: return@composable
            val detailViewModel = koinViewModel<DetailViewModel>(parameters = { parametersOf(id) })
            val saveToFavorite: (FavoriteCats) -> Unit = { cat ->
                detailViewModel.insertFavorite(cat)
            }
            val deleteFromFavorite: (FavoriteCats) -> Unit = { cat ->
                detailViewModel.deleteFavorite(cat)
            }
            val isFav by detailViewModel.isFavorite(id).collectAsStateWithLifecycle(initialValue = false)
            DetailScreen(
                detailUiState = detailViewModel.detailUiState,
                navController = navController,
                id = id,
                navigateBack = {navController.popBackStack()},
                saveToFavorite = saveToFavorite,
                deleteFromFavorite = deleteFromFavorite,
                isFavorite = isFav
            )
        }
        composable(Screens.Favorite.route) {
            val favoriteViewModel = koinViewModel<FavoriteViewModel>()
            val navigateFavToDetail: (FavoriteCats) -> Unit = { favCat ->
                navController.navigate("DetailScreen/${favCat.id}")
            }
            FavoriteScreen(
                favoriteUiState = favoriteViewModel.favoriteUiState,
                navController = navController,
                paddingValues = paddingValues,
                retryAction = {},
                toDetail = navigateFavToDetail
            )
        }
        composable(Screens.Profile.route) {
            ProfileScreen()
        }
    }
}