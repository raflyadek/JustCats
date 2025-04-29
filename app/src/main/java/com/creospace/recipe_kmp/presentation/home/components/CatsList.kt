package com.creospace.recipe_kmp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.ui.theme.RecipekmpTheme

@Composable
fun CatsList(catsList: List<Cats>, navController: NavController, toDetail: (Cats) -> Unit) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        contentPadding = PaddingValues(horizontal = 3.dp),
        content = {
            items(catsList.size) { index ->
                CatsItem(
                    cats = catsList[index],
                    navController = navController,
                    toDetail = {toDetail(catsList[index])}
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    )
}