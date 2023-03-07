package com.donghyun.basic_board_android.dtos

data class DetailsPostDto(
    val postId: Long,
    val title: String,
    val content: String,
    val author: String,
    val authorNickname: String,
    val boardName: String,
    val uploadFilePaths: List<String>,
    val lastModifiedDate: String,
    val parentComments: List<ParentCommentDto>
)