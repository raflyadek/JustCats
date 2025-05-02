package com.creospace.recipe_kmp.presentation.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.creospace.recipe_kmp.data.local.FavoriteCats

@Composable
fun FavoriteCatsList(favoriteCatsList: List<FavoriteCats>, navController: NavController, toDetail: (FavoriteCats) -> Unit) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 10.dp,
        contentPadding = PaddingValues(horizontal = 3.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        content = {
            items(favoriteCatsList.size) { index ->
                val cat = favoriteCatsList[index]
                FavoriteCatsItem(
                    favoriteCats = cat,
                    navController = navController,
                    toDetail = {toDetail(cat)}
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    )
}