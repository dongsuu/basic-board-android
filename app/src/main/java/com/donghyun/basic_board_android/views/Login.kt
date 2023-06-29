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
import com.donghyun.basic_board_android.BoardEmailTextField
import com.donghyun.basic_board_android.BoardEmailValidationTextField
import com.donghyun.basic_board_android.BoardPasswordTextField
import com.donghyun.basic_board_android.utility.BoardMainButton
import com.donghyun.basic_board_android.utility.BoardTitleText
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
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        BoardTitleText(text = "로그인")
        Spacer(modifier = Modifier.padding(20.dp))

        BoardEmailTextField(email = email)
        Spacer(modifier = Modifier.padding(20.dp))

        BoardPasswordTextField(password = password)
        Spacer(modifier = Modifier.padding(20.dp))

        BoardMainButton(onClick = {
            memberViewModel.login(email = email.value, password = password.value, navController)
        }, buttonText = "로그인")
        BoardMainButton(onClick = {
            navController.navigate("join")
        }, buttonText = "회원가입")


    }
}