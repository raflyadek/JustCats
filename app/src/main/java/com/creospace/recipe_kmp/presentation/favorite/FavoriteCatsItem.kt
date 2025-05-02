package com.creospace.recipe_kmp.presentation.favorite

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Size
import com.creospace.recipe_kmp.components.CachedAsyncImage
import com.creospace.recipe_kmp.data.local.FavoriteCats
import com.creospace.recipe_kmp.data.model.Cats

@Composable
fun FavoriteCatsItem(
    favoriteCats: FavoriteCats,
    navController: NavController,
    toDetail: () -> Unit,

) {
    val context = LocalContext.current
    val sizeResolver = rememberConstraintsSizeResolver()
    val painter =ImageRequest.Builder(context)
        .data(favoriteCats.url)
        .memoryCacheKey(favoriteCats.url)
        .diskCacheKey(favoriteCats.url)
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .size(sizeResolver)
        .crossfade(true)
        .build()

    Card(
        modifier = Modifier
            .clickable {
                Log.d("CatsItem", "Navigating to DetailScreen/${favoriteCats.id}")
                toDetail()
            },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) { Column(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = painter,
            contentDescription = "",
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .then(sizeResolver)
        )
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = cats.id.orEmpty(),
//                textAlign = TextAlign.Center
//            )
        }
    }
}
