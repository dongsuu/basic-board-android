package com.donghyun.basic_board_android.dtos

data class MemberJoinDto(
    var name: String = "",
    var age: Int = 0,
    var email: String = "",
    var password: String = "",
    var nickname: String = ""
)
