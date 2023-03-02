package com.donghyun.basic_board_android.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
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
import com.donghyun.basic_board_android.viewModel.HomeViewModel
import com.donghyun.basic_board_android.viewModel.MemberViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    memberViewModel: MemberViewModel,
    homeviewModel: HomeViewModel
){
    Column(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val email = remember {
            mutableStateOf("")
        }

        val password = remember {
            mutableStateOf("")
        }

        Text(
            text = "로그인",
            style = TextStyle(
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(20.dp)
        )

        Spacer(modifier = Modifier.padding(20.dp))

        TextField(
            label = { Text(text = "이메일 (아이디)") },
            value = email.value,
            onValueChange = {e -> email.value = e}
        )

        Spacer(modifier = Modifier.padding(20.dp))

        TextField(
            label = { Text(text = "비밀번호") },
            value = password.value,
            onValueChange = {p -> password.value = p},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.padding(20.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)){
            Button(
                onClick = {
                    memberViewModel.login(email = email.value, password = password.value, navController)
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "로그인")
            }
        }

        Box(modifier = Modifier.padding(10.dp)){
            Button(
                onClick = {
                    navController.navigate("join")
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "회원가입")
            }
        }

    }
}