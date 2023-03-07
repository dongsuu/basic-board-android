package com.donghyun.basic_board_android.repository

import com.donghyun.basic_board_android.apis.CommentApi
import com.donghyun.basic_board_android.apis.PostApi
import com.donghyun.basic_board_android.dtos.CreateCommentDto
import com.donghyun.basic_board_android.service.RetrofitService
import retrofit2.Response
import retrofit2.create

class CommentRepository {

    suspend fun createComment(
        postId: Long,
        accessToken: String,
        comment: CreateCommentDto
    ): Response<String> {
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val commentApi = retrofit.create(CommentApi::class.java)

        return commentApi.createComment(
            postId,
            accessToken,
            comment
        )
    }

    suspend fun deleteComment(
        commentId: Long,
        accessToken: String
    ): Response<String>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val commentApi = retrofit.create(CommentApi::class.java)

        return commentApi.deleteComment(commentId, accessToken)
    }

    suspend fun createReply(
        commentId: Long,
        accessToken: String,
        reply: CreateCommentDto
    ): Response<String>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val commentApi = retrofit.create(CommentApi::class.java)

        return commentApi.createReply(
            commentId,
            accessToken,
            reply
        )
    }

    suspend fun deleteReply(
        commentId: Long,
        accessToken: String
    ): Response<String>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val commentApi = retrofit.create(CommentApi::class.java)

        return commentApi.deleteReply(commentId, accessToken)
    }
}