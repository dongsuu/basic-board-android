package com.donghyun.basic_board_android.views

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.donghyun.basic_board_android.viewModel.MemberViewModel

//@Composable
//fun MyInfoScreen(
//    memberViewModel: MemberViewModel,
//    navController: NavController
//){
//    val myInfo = memberViewModel.getMyInfo()
//    Column(
//        modifier = Modifier
//            .padding(20.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = "내정보",
//            style = TextStyle(
//                fontSize = 40.sp,
//                fontFamily = FontFamily.Cursive,
//                fontWeight = FontWeight.Bold),
//        )
//        Text("이름: " + myInfo.value!!.name)
//        Text("나이: " + myInfo.value!!.age.toString())
//        Text("이메일: " + myInfo.value!!.email )
//        Text("닉네임: " + myInfo.value!!.nickname)
//
//        Button(onClick = { navController.navigate("updateMyInfo") }) {
//            Text(text = "내 정보 변경하기")
//        }
//
//        Spacer(modifier = Modifier.padding(20.dp))
//        Divider(thickness = 1.dp, color = Color.Gray)
//        Spacer(modifier = Modifier.padding(20.dp))
//
//        Text("내가 쓴 글",
//            style = TextStyle(
//                fontSize = 40.sp,
//                fontFamily = FontFamily.Cursive,
//                fontWeight = FontWeight.Bold),
//        )
//
//        myInfo.value!!.posts.forEachIndexed { index, post ->
//            Text(index.toString() + "번째 글", style = TextStyle(fontSize = 12.sp))
//            Text("제목: " + post.title)
//            Text("내용: " + post.content)
//            Spacer(modifier = Modifier.padding(5.dp))
//        }
//
//        Spacer(modifier = Modifier.padding(20.dp))
//        Divider(thickness = 1.dp, color = Color.Gray)
//        Spacer(modifier = Modifier.padding(20.dp))
//
//        Text(text = "내가 작성한 댓글",
//            style = TextStyle(
//                fontSize = 40.sp,
//                fontFamily = FontFamily.Cursive,
//                fontWeight = FontWeight.Bold
//            )
//        )
//        myInfo.value!!.comments.forEachIndexed { index, comment ->
//            Text(index.toString() + "번째 댓글")
//            Text("댓글 내용: " + comment.content)
//            Spacer(modifier = Modifier.padding(5.dp))
//        }
//
//    }
//}

@Composable
fun MyInfoScreen(
    memberViewModel: MemberViewModel,
    navController: NavController
) {
// Retrieve the user's information from the view model
    val myInfo = memberViewModel.getMyInfo()
    // Card layout for the screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Column layout for the screen content
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Title of the screen
            Text(
                text = "내 정보",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            // Display the user's personal information
            Text(
                text = "이름: ${myInfo.value!!.name}",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "나이: ${myInfo.value!!.age}",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "이메일: ${myInfo.value!!.email}",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "닉네임: ${myInfo.value!!.nickname}",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )

            // Button to update the user's information
            Button(
                onClick = { navController.navigate("updateMyInfo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(text = "내 정보 변경하기")
            }

            // Divider for visual separation
            Divider(color = Color.White)

            // Display the user's posts
            Text(
                text = "내가 쓴 글",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                ),
            )

            // Iterate over each post and display its title and content
            myInfo.value!!.posts.forEachIndexed { index, post ->
                Text(
                    text = "${index + 1}번째 글",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black
                    ),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "제목: ${post.title}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                Text(
                    text = "내용: ${post.content}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Divider for visual separation
            Divider(color = Color.Black)

            // Display the user's comments
            Text(
                text = "내가 작성한 댓글",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            )

            // Iterate over each comment and display its content
            myInfo.value!!.comments.forEachIndexed { index, comment ->
                Text(
                    text = "${index + 1}번째 댓",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black
                    ),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "제목: ${comment.content}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}