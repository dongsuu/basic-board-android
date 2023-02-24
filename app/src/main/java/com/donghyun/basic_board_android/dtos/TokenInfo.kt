package com.donghyun.basic_board_android.dtos

data class TokenInfo(
    val grantType: String = "",
    val accessToken: String = "",
    val refreshToken: String = ""
)