package com.donghyun.basic_board_android.apis

import com.donghyun.basic_board_android.dtos.CommentDto
import com.donghyun.basic_board_android.dtos.CreateCommentDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentApi {

    @POST("/apis/comments/new/{postId}")
    suspend fun createComment(
        @Path("postId") postId: Long,
        @Header("Authorization") token: String,
        @Body comment: CreateCommentDto
    ) : Response<String>

    @POST("/apis/comments/delete/{commentId}")
    suspend fun deleteComment(
        @Path("commentId") commentId: Long,
        @Header("Authorization") token: String,
    ) : Response<String>

    @POST("/apis/comments/reply/new/{commentId}")
    suspend fun createReply(
        @Path("commentId") commentId: Long,
        @Header("Authorization") token: String,
        @Body reply: CreateCommentDto
    ) : Response<String>

    @POST("/apis/comments/reply/delete/{commentId}")
    suspend fun deleteReply(
        @Path("commentId") commentId: Long,
        @Header("Authorization") token: String
    ): Response<String>
}