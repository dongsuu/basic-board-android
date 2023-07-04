package com.donghyun.basic_board_android.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "홈", Icons.Filled.Home)
    object Profile : Screen("myInfo", "내 정보", Icons.Filled.Person)
    object Board : Screen("boardList", "게시판", Icons.Filled.Menu)
}