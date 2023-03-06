package com.donghyun.basic_board_android.views

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.donghyun.basic_board_android.viewModel.HomeViewModel
import com.donghyun.basic_board_android.viewModel.MemberViewModel
import com.donghyun.basic_board_android.viewModel.PostViewModel

@Composable
fun PostDetails(
    postViewModel: PostViewModel,
    homeViewModel: HomeViewModel,
    memberViewModel: MemberViewModel,
    navController: NavController
){
    TopAppBar(navController = navController)
    val post = postViewModel.getPostDetails().value!!

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .padding(60.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "제목",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = post.title,
            style = TextStyle(fontSize = 10.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

        Text(
            text = "작성자",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = post.author,
            style = TextStyle(fontSize = 10.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

        Text(
            text = "작성일",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = post.lastModifiedDate,
            style = TextStyle(fontSize = 10.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)


        Text(
            text = "내용",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = post.content,
            style = TextStyle(fontSize = 10.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

        Text(
            text = "게시판 카테고리",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = post.boardName,
            style = TextStyle(fontSize = 10.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

        Text(text = "이미지 파일")
        post.uploadFilePaths.forEach{
            Log.d("TAG", "UploadFilePath: ${it}")
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            if(homeViewModel.getCurrentMemberInfo().value!!.name == post.author){
                Button(onClick = {
                    navController.navigate("updatePost")
                }) {
                    Text(text = "글 수정하기")
                }
                Button(onClick = {
                    postViewModel.deletePost(
                        memberViewModel.getRequestToken().value,
                        postViewModel.getCurrentPostId().value,
                        post.boardName,
                        navController
                    )
                }) {
                    Text(text = "글 삭제")
                }
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

        val content = remember {
            mutableStateOf("")
        }
        Text(
            text = "댓글",
            style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)
        )
        TextField(
            label = { Text(text = "댓글을 입력하세요.") },
            value = content.value,
            onValueChange = {c -> content.value = c}
        )

        Button(onClick = {
            /**
             * TODO Create Comment
             */
        }) {
            Text("댓글 등록")
        }
        for (parentComment in post.parentComments) {
            Divider(color = Color.Gray, thickness = 1.dp)
            Text(
                text = parentComment.authorNickname,
                style = TextStyle(fontSize = 10.sp)
            )
            Text(
                text = parentComment.content,
                style = TextStyle(fontSize = 10.sp)
            )
            Text(
                text = parentComment.createDate,
                style = TextStyle(fontSize = 10.sp)
            )
            Divider(color = Color.Gray, thickness = 0.5.dp)
            for (reply in parentComment.replies) {
                Text(
                    text = reply.replyAuthorNickname,
                    style = TextStyle(fontSize = 10.sp)
                )
                Text(
                    text = reply.content,
                    style = TextStyle(fontSize = 10.sp)
                )
                Text(
                    text = reply.createDate,
                    style = TextStyle(fontSize = 10.sp)
                )
            }
        }



    }
}