package com.donghyun.basic_board_android.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.donghyun.basic_board_android.utility.isValidEmail
import com.donghyun.basic_board_android.viewModel.HomeViewModel
import com.donghyun.basic_board_android.viewModel.MemberViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    memberViewModel: MemberViewModel,
){
    TopAppBar(navController = navController)

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

        val isValidEmail = remember {
            mutableStateOf(false)
        }
        val isValidPassword = remember {
            mutableStateOf(false)
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

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
                isValidEmail.value = it.isValidEmail()
            },
            label = { Text(text = "이메일 (아이디)") },
            modifier = Modifier.fillMaxWidth(),
            isError = !isValidEmail.value,
        )
        if(!isValidEmail.value){
            Text(
                text = "이메일 형식이 잘못되었습니다. (email@xxx.xx)",
                style = TextStyle(color = MaterialTheme.colors.error)
            )
        }

        Spacer(modifier = Modifier.padding(20.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "비밀번호") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            isError = !isValidPassword.value,
        )

        Spacer(modifier = Modifier.padding(20.dp))


        Button(
            onClick = {
                memberViewModel.login(email = email.value, password = password.value, navController)
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "로그인")
        }


        Button(
            onClick = {
                navController.navigate("join")
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "회원가입")
        }


    }
}