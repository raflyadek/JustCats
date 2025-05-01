package com.creospace.recipe_kmp.presentation.home.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Size
import com.creospace.recipe_kmp.components.Margin
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.ui.theme.Black
import com.creospace.recipe_kmp.ui.theme.Grey
import com.creospace.recipe_kmp.ui.theme.LightGrey
import com.creospace.recipe_kmp.ui.theme.RecipekmpTheme
import com.creospace.recipe_kmp.ui.theme.Red
import com.creospace.recipe_kmp.ui.theme.White

@Composable
fun CatsItem(cats: Cats, navController: NavController, toDetail: () -> Unit) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(cats.url)
        .memoryCacheKey(cats.url)
        .diskCacheKey(cats.url)
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()
//    val breed = cats.breeds.firstOrNull()
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable {
                Log.d("CatsItem", "Navigating to DetailScreen/${cats.id}")
                toDetail()
            },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier
        ) {
            AsyncImage(
                model = imageRequest,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentDescription = "food-image",
                contentScale = ContentScale.Crop
            )
            // #ISSUE# java.util.NoSuchElementException: No value present jetpack compose
//            Margin(size = 10.dp)
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = breed?.description.orEmpty(),
//                fontSize = 40.sp,
//                textAlign = TextAlign.Center,
//            )
        }
    }
}


