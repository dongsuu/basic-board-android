package com.donghyun.basic_board_android.dtos

data class CreatePostDto(
    val title : String = "",
    val content : String = "",
    val boardName : String = ""
)
