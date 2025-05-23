package com.creospace.recipe_kmp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.request.crossfade
import coil3.size.Size

@Composable
fun CachedAsyncImage(
    url: String,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader(context)
    var aspectRatio by remember(url) { mutableStateOf(ImageDimensionsCache.getAspectRatio(url) ?: 1f) }
    var isLoading by remember(url) { mutableStateOf(ImageDimensionsCache.getAspectRatio(url) == null) }

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .memoryCacheKey(url)
            .listener(
                onSuccess = { _, result ->
                    val drawable = result.image
                    val imageWidth = drawable.width.toFloat()
                    val imageHeight = drawable.height.toFloat()
                    val calculatedRatio = if (imageWidth > 0f && imageHeight > 0f) {
                        imageWidth / imageHeight
                    } else {
                        1f
                    }

                    ImageDimensionsCache.setAspectRatio(url, calculatedRatio)
                    aspectRatio = calculatedRatio
                    isLoading = false
                }
            )
            .build(),
        contentDescription = "",
        contentScale = contentScale,
        modifier = modifier
    )
}