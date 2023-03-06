package com.donghyun.basic_board_android.dtos

data class CommentDto(
    val commentId: Long,
    val content: String,
    val authorNickname: String,
    val createDate: String
)
