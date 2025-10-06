package pe.edu.upc.easymovie.features.main

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val name: String,
    val icon: ImageVector,
    val route: String
)