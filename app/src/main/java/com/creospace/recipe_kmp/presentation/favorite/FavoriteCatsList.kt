package com.creospace.recipe_kmp.presentation.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.creospace.recipe_kmp.data.local.FavoriteCats
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.presentation.home.components.CatsItem

@Composable
fun FavoriteCatsList(FavoriteCatsList: List<FavoriteCats>, navController: NavController, toDetail: (FavoriteCats) -> Unit) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 10.dp,
        contentPadding = PaddingValues(horizontal = 3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        content = {
            items(FavoriteCatsList.size) { index ->
                FavoriteCatsItem(
                    favoriteCats = FavoriteCatsList[index],
                    navController = navController,
                    toDetail = {toDetail(FavoriteCatsList[index])}
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    )
}