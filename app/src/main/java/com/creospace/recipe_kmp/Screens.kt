package com.creospace.recipe_kmp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val title: String, val icon: ImageVector? = null) {
    data object Home: Screens(route = "HomeScreen", title = "Beranda", Icons.Filled.Home)
    data class Detail(val id: String): Screens(route = "DetailScreen/$id", title = "Detail")
    data object Favorite: Screens(route = "FavoriteScreen", title = "Disukai", Icons.Filled.Favorite)
    data object Profile: Screens(route = "ProfileScreen", title = "Akun", Icons.Filled.Person)
}