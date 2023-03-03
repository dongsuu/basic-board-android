package com.donghyun.basic_board_android.views

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.donghyun.basic_board_android.dtos.PostDto
import com.donghyun.basic_board_android.viewModel.HomeViewModel
import com.donghyun.basic_board_android.viewModel.MemberViewModel
import com.donghyun.basic_board_android.viewModel.PostViewModel

@Composable
fun PostHome(
    navController: NavHostController,
    boardName: String?,
    postViewModel: PostViewModel,
    posts: MutableState<List<PostDto>?>,
    memberViewModel: MemberViewModel
) {

    TopAppBar(navController = navController)

    postViewModel.getPostsByBoardName(memberViewModel.getRequestToken().value, boardName!!)

    var boardNameKor = ""

    Column(
        modifier = Modifier
            .padding(60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        when (boardName) {
            "FREE" -> {
                boardNameKor = "자유 게시판"
            }
            "STUDY" -> {
                boardNameKor = "스터디 게시판"
            }
            "SPORTS" -> {
                boardNameKor = "스포츠 게시판"
            }
        }
        Text(
            text = boardNameKor,
            style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)
        )

        Row(){
            Button(onClick = { navController.navigate("createPost/${boardName}") }) {
                Text(text = "글 쓰기")
            }
        }

        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            posts.value?.forEach {
                Log.d("TAG", "Posts size: ${posts.value!!.size}")
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = it.title,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )
                        Text(text = it.content)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Divider(color = Black, thickness = 1.dp)
                    }
                }
            }
        }


    }
}