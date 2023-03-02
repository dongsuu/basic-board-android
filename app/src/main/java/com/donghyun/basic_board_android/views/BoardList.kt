package com.donghyun.basic_board_android.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BoardListScreen(
    navController: NavController
){
    Column {
        ClickableText(
            text = AnnotatedString("자유 게시판"),
            modifier = Modifier.padding(10.dp),
            onClick = {

            }
        )
        ClickableText(
            text = AnnotatedString("스터디 게시판"),
            modifier = Modifier.padding(10.dp),
            onClick = {

            }
        )
        ClickableText(
            text = AnnotatedString("스포츠 게시판"),
            modifier = Modifier.padding(10.dp),
            onClick = {

            }
        )
    }
}