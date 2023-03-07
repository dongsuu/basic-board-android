package com.donghyun.basic_board_android.dtos

import java.time.LocalDateTime

data class UpdatePostDto(
    val title: String,
    val content: String,
    val boardName: String,
    val uploadFilePaths: List<String>,
    val author: String,
    val lastModifiedDate: String
)
