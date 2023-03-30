package com.donghyun.basic_board_android.dtos

data class UpdateMemberDto(
    val email: String,
    val name: String,
    val age: Int,
    val nickname: String
)
