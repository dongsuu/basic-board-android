package com.donghyun.basic_board_android.views

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.donghyun.basic_board_android.dtos.HomeDto
import com.donghyun.basic_board_android.dtos.MemberJoinDto
import com.donghyun.basic_board_android.viewModel.MemberViewModel


@Composable
fun JoinScreen(
    memberViewModel: MemberViewModel,
    memberJoinResponse: MutableState<MemberJoinDto?>,
    navController: NavController
){
    Column(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val name = remember{
            mutableStateOf("")
        }

        val email = remember {
            mutableStateOf("")
        }

        val password = remember {
            mutableStateOf("")
        }

        val nickname = remember {
            mutableStateOf("")
        }

        val age = remember {
            mutableStateOf("")
        }

        Text(
            text = "회원가입",
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
            label = { Text(text = "비밀번호") },
            value = password.value,
            onValueChange = {p -> password.value = p},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
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

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)){
            Button(
                onClick = {
                    Log.d(TAG, "JoinScreen: Join")
                    memberViewModel.joinMember(
                        name = name.value,
                        email = email.value,
                        password = password.value,
                        nickname = nickname.value,
                        age = age.value.toInt()
                    )
                    navController.navigate("login")
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "회원가입 완료")
            }
        }
    }
}