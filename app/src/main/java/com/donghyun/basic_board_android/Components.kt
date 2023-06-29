package com.donghyun.basic_board_android

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.donghyun.basic_board_android.utility.*

@Composable
fun BoardEmailValidationTextField(
    email: MutableState<String>,
    isValidEmail: MutableState<Boolean>
){
    return OutlinedTextField(
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
}

@Composable
fun BoardPasswordValidationTextField(
    password: MutableState<String>,
    isValidPassword: MutableState<Boolean>
){
    return OutlinedTextField(
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
}

@Composable
fun BoardNameValidationTextField(
    name: MutableState<String>,
    isValidName: MutableState<Boolean>
){
    return OutlinedTextField(
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
}

@Composable
fun BoardNicknameValidationTextField(
    nickname: MutableState<String>,
    isValidNickname: MutableState<Boolean>
){
    return OutlinedTextField(
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
}

@Composable
fun BoardAgeValidationTextField(
    age: MutableState<String>,
    isValidAge: MutableState<Boolean>
){
    return OutlinedTextField(
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
}

@Composable
fun BoardPasswordTextField(
    password: MutableState<String>
){
    return OutlinedTextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = { Text(text = "비밀번호") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun BoardEmailTextField(
    email: MutableState<String>
){
    return OutlinedTextField(
        value = email.value,
        onValueChange = { email.value = it },
        label = { Text(text = "이메일 (아이디)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
    )
}