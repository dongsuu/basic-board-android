package com.donghyun.basic_board_android.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BoardListScreen(
    navController: NavController
){
    TopAppBar(navController = navController)
    Column(
        modifier = Modifier
            .padding(60.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClickableText(
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
            text = AnnotatedString("자유 게시판"),
            modifier = Modifier.padding(10.dp),
            onClick = {
                navController.navigate("postHome/FREE")
            }
        )
        Divider(color = Color.Black, thickness = 1.dp)
        Spacer(modifier = Modifier.padding(10.dp))

        ClickableText(
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
            text = AnnotatedString("스터디 게시판"),
            modifier = Modifier.padding(10.dp),
            onClick = {
                navController.navigate("postHome/STUDY")
            }
        )
        Divider(color = Color.Black, thickness = 1.dp)
        Spacer(modifier = Modifier.padding(10.dp))

        ClickableText(
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
            text = AnnotatedString("스포츠 게시판"),
            modifier = Modifier.padding(10.dp),
            onClick = {
                navController.navigate("postHome/SPORTS")
            }
        )
    }
}