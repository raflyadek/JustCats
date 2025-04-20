package com.creospace.recipe_kmp.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.creospace.recipe_kmp.components.Margin
import com.creospace.recipe_kmp.components.TopBar
import com.creospace.recipe_kmp.data.model.Breed
import com.creospace.recipe_kmp.data.model.Cats
import com.creospace.recipe_kmp.presentation.home.components.ErrorScreen
import com.creospace.recipe_kmp.ui.theme.RecipekmpTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    id: String,
    detailUiState: DetailUiState,
    navigateBack: () -> Unit,
    saveToFavorite: () -> Unit,
    deleteFromFavorite: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                title = "detail",
                navigateBack = navigateBack
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.consumeWindowInsets(innerPadding)) {
            when (detailUiState) {
                is DetailUiState.Loading -> CircularProgressIndicator()
                is DetailUiState.Success ->
                    DetailScreenContent(
                        cats = detailUiState.detail,
                        paddingValues = innerPadding,
                        saveToFavorite = saveToFavorite,
                        deleteFromFavorite = deleteFromFavorite
                    )
                else -> ErrorScreen({})
            }
        }
    }
}

@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    cats: Cats,
    paddingValues: PaddingValues,
    saveToFavorite: () -> Unit,
    deleteFromFavorite: () -> Unit,
    isFav: Boolean = false
) {
    val breed = cats.breeds.firstOrNull()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ){
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(cats.url)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            Margin(size = 12.dp)
            if (isFav) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    "",
                    modifier = Modifier.clickable {
                        saveToFavorite()
                    }
                )
            } else {
                Icon(
                    Icons.Default.Favorite,
                    "",
                    modifier = Modifier.clickable {
                        deleteFromFavorite()
                    }
                )
            }
            Margin(size = 16.dp)
            Text(
                text = "Name",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = breed?.name.orEmpty(),
                fontSize = 15.sp,
            )

            Margin(size = 16.dp)
            Text(
                text = "Temprament",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = breed?.temperament.orEmpty(),
                fontSize = 15.sp
            )

            Margin(size = 16.dp)
            Text(
                text = "Description",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = breed?.description.orEmpty(),
                fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenContentPreview(modifier: Modifier = Modifier) {
    RecipekmpTheme {
        //doesnt work because coil load it from network and the preview dont load network images
        val navController = rememberNavController()
        //create the fake cat object using Cats data class
        val mockCat = Cats(
            url = "https://cdn2.thecatapi.com/images/MTY30DIyMO.jpg",
            width = 200,
            height = 300,
            breeds = listOf(
                Breed(
                    name = "abyssinian",
                    temperament = "active, energetic, cute, intelligent",
                    description = "the abysnian is easy to care for and blablablalba"
                )
            )
        )
        DetailScreenContent(
            cats = mockCat,
            paddingValues = PaddingValues(),
            saveToFavorite = {},
            deleteFromFavorite = {},
            isFav = true
        )
    }
}