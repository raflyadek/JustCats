package com.creospace.recipe_kmp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.creospace.recipe_kmp.R
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.presentation.home.components.CatsItem
import com.creospace.recipe_kmp.presentation.home.components.CatsList
import com.creospace.recipe_kmp.presentation.home.components.ErrorScreen
import com.creospace.recipe_kmp.presentation.home.components.LoadingScreen

@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    toDetail: (Cats) -> Unit,
) {
    when (homeUiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Success ->
            CatsList(
                catsList = homeUiState.cats,
                navController = navController,
                toDetail = toDetail
            )
        else -> ErrorScreen(retryAction)
    }
}