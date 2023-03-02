package com.donghyun.basic_board_android.dtos

data class HomeDto(
    var nickname: String = "",
    var postCount: Int = 0,
    var commentCount: Int = 0
)