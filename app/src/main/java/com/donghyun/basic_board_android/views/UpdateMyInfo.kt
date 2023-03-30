package com.donghyun.basic_board_android.views

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.donghyun.basic_board_android.dtos.MyInfoDto
import com.donghyun.basic_board_android.viewModel.MemberViewModel

@Composable
fun UpdateMyInfo(
    memberViewModel: MemberViewModel,
    navController: NavController
){
    val prevInfo: MyInfoDto = memberViewModel.getMyInfo().value!!

    val name = remember{
        mutableStateOf(prevInfo.name)
    }

    val email = remember {
        mutableStateOf(prevInfo.email)
    }

    val nickname = remember {
        mutableStateOf(prevInfo.nickname)
    }

    val age = remember {
        mutableStateOf(prevInfo.age.toString())
    }

    TopAppBar(navController = navController)
    Column {
        Text(
            text = "내 정보 수정",
            style = TextStyle(
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(20.dp)
        )

        Spacer(modifier = Modifier.padding(20.dp))

        TextField(
            label = { Text(text = "이름") },
            value = name.value,
            onValueChange = {n -> name.value = n}
        )


        TextField(
            label = { Text(text = "이메일 (아이디)") },
            value = email.value,
            onValueChange = {e -> email.value = e}
        )

        TextField(
            label = { Text(text = "닉네임") },
            value = nickname.value,
            onValueChange = {nk -> nickname.value = nk}
        )

        TextField(label = { Text(text = "나이") },
            value = age.value,
            onValueChange = {a -> age.value = a}
        )

        Spacer(modifier = Modifier.padding(20.dp))

        Button(
            onClick = {
                memberViewModel.updateMyInfo(navController, email.value, name.value, age.value.toInt(), nickname.value)
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "변경 완료")
        }
    }
}