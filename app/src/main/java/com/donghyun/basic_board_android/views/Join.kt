package com.donghyun.basic_board_android.views

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.donghyun.basic_board_android.dtos.MemberJoinDto
import com.donghyun.basic_board_android.utility.*
import com.donghyun.basic_board_android.viewModel.MemberViewModel

@Composable
fun JoinScreen(
    memberViewModel: MemberViewModel,
    memberJoinResponse: MutableState<MemberJoinDto?>,
    navController: NavController
) {
    val scrollState = rememberScrollState()

    TopAppBar(navController = navController)
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(top = 75.dp, start = 16.dp)
    ) {

        Text(
            text = "회원가입",
            style = MaterialTheme.typography.h4,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        val name = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val nickname = remember { mutableStateOf("") }
        val age = remember { mutableStateOf("") }
        val isValidName = remember {
            mutableStateOf(false)
        }
        val isValidNickname = remember {
            mutableStateOf(false)
        }
        val isValidAge = remember {
            mutableStateOf(false)
        }
        val isValidEmail = remember {
            mutableStateOf(false)
        }
        val isValidPassword = remember {
            mutableStateOf(false)
        }
        val errorMessages = remember { // 리스트로 !
            mutableStateListOf("")
        }
        var isTotalError = remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            value = name.value,
            onValueChange = {
                name.value = it
                isValidName.value = it.isValidName()
            },
            label = { Text(text = "이름") },
            modifier = Modifier.fillMaxWidth(),
            isError = !isValidName.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if(isValidName.value) Color(android.graphics.Color.parseColor("#18bc9c"))
                else MaterialTheme.colors.error,
                unfocusedBorderColor = if(isValidName.value) Color(android.graphics.Color.parseColor("#18bc9c"))
                else MaterialTheme.colors.error , // 포커스되지 않은 상태의 테두리 색상
            ),
            trailingIcon = {
                if(isValidName.value){
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(android.graphics.Color.parseColor("#18bc9c"))
                    )
                } else{
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
                isValidEmail.value = it.isValidEmail()
            },
            label = { Text(text = "이메일 (아이디)") },
            modifier = Modifier.fillMaxWidth(),
            isError = !isValidEmail.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if(isValidEmail.value) Color(android.graphics.Color.parseColor("#18bc9c"))
                else MaterialTheme.colors.error,
                unfocusedBorderColor = if(isValidEmail.value) Color(android.graphics.Color.parseColor("#18bc9c"))
                else MaterialTheme.colors.error , // 포커스되지 않은 상태의 테두리 색상
            ),
            trailingIcon = {
                if(isValidEmail.value){
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(android.graphics.Color.parseColor("#18bc9c"))
                    )
                } else{
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        )

        if(!isValidEmail.value){
            Text(
                text = "이메일 형식이 잘못되었습니다. (email@xxx.xx)",
                style = TextStyle(color = MaterialTheme.colors.error)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
                isValidPassword.value = it.isValidPassword()
            },
            label = { Text(text = "비밀번호") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = !isValidPassword.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if(isValidPassword.value) Color(android.graphics.Color.parseColor("#18bc9c"))
                else MaterialTheme.colors.error,
                unfocusedBorderColor = if(isValidPassword.value) Color(android.graphics.Color.parseColor("#18bc9c"))
                else MaterialTheme.colors.error , // 포커스되지 않은 상태의 테두리 색상
            ),
            trailingIcon = {
                if(isValidPassword.value){
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(android.graphics.Color.parseColor("#18bc9c"))
                    )
                } else{
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        )
        if(!isValidPassword.value){
            Text(
                text = "비밀번호는 6자 이상 입력해야합니다.",
                style = TextStyle(color = MaterialTheme.colors.error)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nickname.value,
            onValueChange = {
                nickname.value = it
                isValidNickname.value = it.isValidNickname()
            },
            label = { Text(text = "닉네임") },
            modifier = Modifier.fillMaxWidth(),
            isError = !isValidNickname.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if(isValidNickname.value) Color(android.graphics.Color.parseColor("#18bc9c"))
                else MaterialTheme.colors.error,
                unfocusedBorderColor = if(isValidNickname.value) Color(android.graphics.Color.parseColor("#18bc9c"))
                else MaterialTheme.colors.error , // 포커스되지 않은 상태의 테두리 색상
            ),
            trailingIcon = {
                if(isValidNickname.value){
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(android.graphics.Color.parseColor("#18bc9c"))
                    )
                } else{
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = age.value,
            onValueChange = {
                age.value = it
                isValidAge.value = it.isValidAge()
            },
            label = { Text(text = "나이") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = !isValidAge.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if(isValidAge.value) Color(android.graphics.Color.parseColor("#18bc9c"))
                else MaterialTheme.colors.error,
                unfocusedBorderColor = if(isValidAge.value) Color(android.graphics.Color.parseColor("#18bc9c"))
                else MaterialTheme.colors.error , // 포커스되지 않은 상태의 테두리 색상
            ),
            trailingIcon = {
                if(isValidAge.value){
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(android.graphics.Color.parseColor("#18bc9c"))
                    )
                } else{
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))
        val openDialog = remember { mutableStateOf(false) }


        if(openDialog.value){
            DialogContent({openDialog.value = false}, errorMessages)
        } else {
            errorMessages.clear()
        }
        Button(
            onClick = {
                var hasTotalError = false
                if(!isValidName.value){
                    errorMessages.add("유효하지 않는 이름입니다.")
                    hasTotalError = true
                }
                if(!isValidEmail.value){
                    errorMessages.add("유효하지 않는 이메일입니다.")
                    hasTotalError = true
                }
                if(!isValidNickname.value){
                    errorMessages.add("유효하지 않는 닉네임입니다.")
                    hasTotalError = true
                }
                if(!isValidPassword.value){
                    errorMessages.add("유효하지 않는 비밀번호입니다.")
                    hasTotalError = true
                }
                if(!isValidAge.value){
                    errorMessages.add("유효하지 않는 나이입니다.")
                    hasTotalError = true
                }

                if(!hasTotalError){
                    memberViewModel.joinMember(
                        name = name.value,
                        email = email.value,
                        password = password.value,
                        nickname = nickname.value,
                        age = age.value.toIntOrNull() ?: 0
                    )
                    navController.navigate("login")   
                } else{
                    isTotalError.value = true
                    openDialog.value = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "회원가입 완료")
        }
    }
}

@Composable
fun DialogContent(onDismissRequest: () -> Unit, errorMessages: SnapshotStateList<String>) {
    // Modal 창의 내용을 구성하는 Composable을 작성합니다.
    // 예: Text, Button 등을 포함한 내용
    // onClose 함수를 호출하여 Modal 창을 닫을 수 있도록 구현합니다.
    Log.d(TAG, "ModalContent: ${errorMessages.size}")
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val boxWidth = screenWidth / 2
    val boxHeight = screenHeight / 3

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .width(boxWidth)
                .height(boxHeight)
                .background(MaterialTheme.colors.surface),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column() {
                Row {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colors.error,
                    )
                    Text(text = "Error!", style = TextStyle(fontSize = 24.sp))
                }
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
            }


            Column() {
                errorMessages.forEach{
                    Text(
                        text = it,
                        style = TextStyle(color = MaterialTheme.colors.error)
                    )
                }
            }

            Button(
                onClick = onDismissRequest,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("닫기")
            }
        }
    }
}