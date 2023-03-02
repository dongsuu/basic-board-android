package com.donghyun.basic_board_android.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.donghyun.basic_board_android.dtos.HomeDto
import com.donghyun.basic_board_android.viewModel.HomeViewModel


@Composable
fun Home(
    navController: NavController,
    currentMemberInfo: MutableState<HomeDto?>
){

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row {
            Text(
                text = "Basic-Board",
                style= TextStyle(
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }

        Column {
            Text(
                text = "환영합니다. " + currentMemberInfo.value?.nickname + "님",
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = "내 게시글 개수 : " + currentMemberInfo.value?.postCount + " 개",
                modifier = Modifier.padding(10.dp)
                )
            Text(
                text = "내 댓글 개수 : " + currentMemberInfo.value?.commentCount + " 개",
                modifier = Modifier.padding(10.dp)
                )

            Spacer(modifier = Modifier.padding(40.dp))

            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text("게시판 목록")
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "내 정보")
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "로그아웃")
                }
            }
        }
    }
}