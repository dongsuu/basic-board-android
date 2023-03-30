package com.donghyun.basic_board_android.dtos

data class MyInfoDto(
    val name: String,
    val email: String,
    val age: Int,
    val nickname: String,
    val posts: List<PostDto>,
    val comments: List<CreateCommentDto>
)
