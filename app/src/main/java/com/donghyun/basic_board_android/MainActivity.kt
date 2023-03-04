package com.donghyun.basic_board_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.donghyun.basic_board_android.dtos.MemberJoinDto
import com.donghyun.basic_board_android.repository.MemberRepository
import com.donghyun.basic_board_android.repository.PostRepository
import com.donghyun.basic_board_android.viewModel.HomeViewModel
import com.donghyun.basic_board_android.viewModel.MemberViewModel
import com.donghyun.basic_board_android.viewModel.PostViewModel
import com.donghyun.basic_board_android.views.*

class MainActivity : ComponentActivity() {
    private val memberRepository : MemberRepository = MemberRepository()
    private val homeViewModel: HomeViewModel = HomeViewModel(memberRepository)
    private val memberViewModel: MemberViewModel = MemberViewModel(memberRepository, homeViewModel)
    private val postRepository : PostRepository = PostRepository()
    private val postViewModel: PostViewModel = PostViewModel(postRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation(
                memberViewModel = memberViewModel,
                memberJoinResponse = memberViewModel.getMemberJoinResponse(),
                homeViewModel = homeViewModel,
                postViewModel = postViewModel
                )
        }
    }
}

@Composable
fun Navigation(
    memberViewModel: MemberViewModel,
    memberJoinResponse: MutableState<MemberJoinDto?>,
    homeViewModel: HomeViewModel,
    postViewModel: PostViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){ LoginScreen(navController = navController, memberViewModel, homeviewModel = homeViewModel) }
        composable("join"){ JoinScreen(memberViewModel = memberViewModel, memberJoinResponse = memberJoinResponse, navController = navController) }
        composable("home"){ Home(navController = navController, currentMemberInfo = homeViewModel.getCurrentMemberInfo()) }
        composable("boardList") { BoardListScreen(navController = navController) }
        composable(
            "postHome/{boardName}",
            arguments = listOf(navArgument("boardName"){type = NavType.StringType})
        ){ backStackEntry ->
            PostHome(navController = navController, backStackEntry.arguments?.getString("boardName"), postViewModel, postViewModel.getPosts(), memberViewModel)
        }
        composable(
            "createPost/{boardName}",
            arguments = listOf(navArgument("boardName"){type = NavType.StringType})
        ){ backStackEntry ->
            PostForm(
                postViewModel = postViewModel,
                navController = navController,
                imageUri = postViewModel.getImageUri(),
                memberViewModel = memberViewModel,
                boardName = backStackEntry.arguments?.getString("boardName")
            )}

        composable("postDetails"){ PostDetails(postViewModel = postViewModel, homeViewModel,navController)}
    }
}
