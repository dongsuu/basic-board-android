package com.donghyun.basic_board_android.dtos

data class HomeDto(
    var name: String ="",
    var email: String = "",
    var age: Int = 0,
    var nickname: String = "",
    var postCount: Int = 0,
    var commentCount: Int = 0
)