package com.donghyun.basic_board_android.views

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.donghyun.basic_board_android.constants.Screen
import com.donghyun.basic_board_android.viewModel.HomeViewModel
import com.donghyun.basic_board_android.viewModel.MemberViewModel


@Composable
fun BottomAppBar(
    navController: NavController,
    memberViewModel: MemberViewModel,
    homeViewModel: HomeViewModel
){
    val items = listOf(
        Screen.Home,
        Screen.Profile,
        Screen.Board
    )

    return Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if(screen.route == "myInfo"){
                                memberViewModel.myInfo(memberViewModel.getRequestToken().value, navController)
                            } else if(screen.route == "home"){
                                homeViewModel.getHome(memberViewModel.getRequestToken().value, navController)
                            } else if(screen.route == "boardList"){
                                navController.navigate("BoardList")
                            }
                        }
                    )
                }
            }
        },
    ) {

    }
}