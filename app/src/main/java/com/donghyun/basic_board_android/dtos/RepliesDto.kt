package com.donghyun.basic_board_android.dtos

data class RepliesDto(
    val commentId: Long,
    val replyAuthorNickname: String,
    val content: String,
    val createDate: String
)
