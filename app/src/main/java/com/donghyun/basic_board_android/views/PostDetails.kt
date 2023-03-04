package com.donghyun.basic_board_android.views

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    navController: NavController
){
    TopAppBar(navController = navController)
    val post = postViewModel.getPostDetails().value!!

    Column(
        modifier = Modifier.padding(60.dp),
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
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "글 수정하기")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "글 삭제")
                }
            }
        }


    }
}