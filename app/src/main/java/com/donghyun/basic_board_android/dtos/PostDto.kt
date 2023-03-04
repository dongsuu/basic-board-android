package com.donghyun.basic_board_android.dtos

import java.time.LocalDateTime

data class PostDto(
    val id: Long,
    val title: String,
    val content: String,
)
