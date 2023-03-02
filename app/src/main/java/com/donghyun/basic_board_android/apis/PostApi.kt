package com.donghyun.basic_board_android.apis

import com.donghyun.basic_board_android.dtos.PostDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST

interface PostApi {
    @POST("/apis/posts/new")
    suspend fun createPost(

    ) : Response<PostDto>

    @POST("/apis/posts/update/{postId}")
    suspend fun updatePost()

    @POST("/apis/posts/delete/{postId}")
    suspend fun deletePost(

    )
}