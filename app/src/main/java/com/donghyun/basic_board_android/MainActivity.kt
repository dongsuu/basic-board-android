package com.donghyun.basic_board_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.donghyun.basic_board_android.dtos.MemberJoinDto
import com.donghyun.basic_board_android.repository.MemberRepository
import com.donghyun.basic_board_android.viewModel.HomeViewModel
import com.donghyun.basic_board_android.views.JoinScreen
import com.donghyun.basic_board_android.views.LoginScreen
import com.donghyun.basic_board_android.viewModel.MemberViewModel
import com.donghyun.basic_board_android.views.Home

class MainActivity : ComponentActivity() {
    private val memberRepository : MemberRepository = MemberRepository()
    private val homeViewModel: HomeViewModel = HomeViewModel(memberRepository)
    private val memberViewModel: MemberViewModel = MemberViewModel(memberRepository, homeViewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation(
                memberViewModel = memberViewModel,
                memberJoinResponse = memberViewModel.getMemberJoinResponse(),
                homeViewModel = homeViewModel
                )
        }
    }
}

@Composable
fun Navigation(
    memberViewModel: MemberViewModel,
    memberJoinResponse: MutableState<MemberJoinDto?>,
    homeViewModel: HomeViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){ LoginScreen(navController = navController, memberViewModel, homeviewModel = homeViewModel) }
        composable("join"){ JoinScreen(memberViewModel = memberViewModel, memberJoinResponse = memberJoinResponse, navController = navController) }
        composable("home"){ Home(navController = navController, currentMemberInfo = homeViewModel.getCurrentMemberInfo()) }
    }
}
