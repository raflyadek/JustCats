package com.creospace.recipe_kmp.presentation.favorite

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.creospace.recipe_kmp.data.local.FavoriteCats
import com.creospace.recipe_kmp.data.model.Cats

@Composable
fun FavoriteCatsItem(favoriteCats: FavoriteCats, navController: NavController, toDetail: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Log.d("CatsItem", "Navigating to DetailScreen/${favoriteCats.id}")
                toDetail()
            },
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(favoriteCats.url)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentDescription = "food-image",
                contentScale = ContentScale.Crop
            )
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = cats.id.orEmpty(),
//                textAlign = TextAlign.Center
//            )
        }
    }
}
