package com.donghyun.basic_board_android.dtos

import java.time.LocalDateTime

data class ParentCommentDto(
    val commentId: Long,
    val authorNickname: String,
    val content: String,
    val createDate: String,
    val replies: List<RepliesDto>
)
