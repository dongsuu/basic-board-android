package com.donghyun.basic_board_android.utility

fun String.isValidEmail(): Boolean{
    val emailRegex = Regex(pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    return matches(emailRegex)
}

fun String.isValidPassword(): Boolean{
    return length >= 6
}

fun String.isValidName(): Boolean{
    return length > 0
}

fun String.isValidNickname(): Boolean{
    return length > 0
}

fun String.isValidAge(): Boolean{
    return this.toIntOrNull() != null
}