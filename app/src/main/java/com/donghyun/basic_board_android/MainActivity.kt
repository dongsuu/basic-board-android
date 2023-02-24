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
import com.donghyun.basic_board_android.views.JoinScreen
import com.donghyun.basic_board_android.views.LoginScreen
import com.donghyun.basic_board_android.views.MemberViewModel

class MainActivity : ComponentActivity() {
    private val memberRepository : MemberRepository = MemberRepository()
    private val memberViewModel: MemberViewModel = MemberViewModel(memberRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation(memberViewModel = memberViewModel,
                memberJoinResponse = memberViewModel.getMemberJoinResponse())
        }
    }
}

@Composable
fun Navigation(
    memberViewModel: MemberViewModel,
    memberJoinResponse: MutableState<MemberJoinDto?>
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){ LoginScreen(navController = navController, memberViewModel) }
        composable("join"){ JoinScreen(memberViewModel = memberViewModel, memberJoinResponse = memberJoinResponse, navController = navController) }
    }
}
