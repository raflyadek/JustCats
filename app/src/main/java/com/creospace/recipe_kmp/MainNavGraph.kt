package com.creospace.recipe_kmp

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.presentation.detail.DetailScreen
import com.creospace.recipe_kmp.presentation.detail.DetailViewModel
import com.creospace.recipe_kmp.presentation.favorite.FavoriteScreen
import com.creospace.recipe_kmp.presentation.home.HomeScreen
import com.creospace.recipe_kmp.presentation.home.HomeViewModel
import com.creospace.recipe_kmp.presentation.profile.ProfileScreen


@Composable
fun MainNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            val homeViewModel: HomeViewModel =
                viewModel(factory = HomeViewModel.Factory)
            HomeScreen(
                homeUiState = homeViewModel.homeUiState,
                navController = navController,
                paddingValues = paddingValues,
                retryAction = {},
                toDetail = { cat ->
                    navController.navigate("DetailScreen/${cat.id}")
                }
            )
        }
        composable(
            route = "DetailScreen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("id") ?: return@composable
            val context = LocalContext.current.applicationContext as Application
            val detailViewModel: DetailViewModel =
                viewModel(factory = DetailViewModel.provideFactory(context, id))
            DetailScreen(
                detailUiState = detailViewModel.detailUiState,
                navController = navController,
                id = id,
                navigateBack = {navController.popBackStack()}
            )
        }

    }
}