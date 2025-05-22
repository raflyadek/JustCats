package com.creospace.recipe_kmp

import android.accessibilityservice.AccessibilityService.ScreenshotResult
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.creospace.recipe_kmp.ui.theme.RecipekmpTheme
import kotlinx.coroutines.flow.map
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipekmpTheme {
                KoinContext() {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val mainScreens = listOf(Screens.Home, Screens.Favorite, Screens.Profile)
    val bottomBarRoutes = setOf(Screens.Home.route, Screens.Favorite.route, Screens.Profile.route)
    val navController = rememberNavController()
    val currentRouteFlow = remember {
        navController.currentBackStackEntryFlow
            .map { it.destination.route }
    }
    val currentRoute by currentRouteFlow.collectAsStateWithLifecycle(initialValue = Screens.Home.route)
    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                MainNavigationBar(navController = navController, screens = mainScreens)
            }
        }
    ) { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            MainNavGraph(navController = navController, paddingValues = paddingValue)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(modifier: Modifier = Modifier) {
        RecipekmpTheme {
            MainScreen()
        }
}
