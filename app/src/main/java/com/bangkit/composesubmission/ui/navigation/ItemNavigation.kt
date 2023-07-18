package com.bangkit.composesubmission.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class ItemNavigation(
    val title: String,
    val icon: ImageVector,
    val screen: Screen,
    val contentDescription: String
)