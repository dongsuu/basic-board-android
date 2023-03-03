package com.donghyun.basic_board_android.apis

import com.donghyun.basic_board_android.dtos.PostDto
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import java.io.File

interface PostApi {

    @GET("/apis/posts/{boardName}")
    suspend fun getPostsByBoardName(
        @Path("boardName") boardName: String,
        @Header("Authorization") token: String
    ) : Response<List<PostDto>>

    @Multipart
    @POST("/apis/posts/new")
    suspend fun createPostWithImages(
        @Part("data") post: PostDto,
        @Part images: List<MultipartBody.Part>,
        @Header("Authorization") token: String
    ) : Response<String>

    @Multipart
    @POST("/apis/posts/new")
    suspend fun createPost(
        @Part("data") post: PostDto,
        @Header("Authorization") token: String
    ) : Response<String>

    @POST("/apis/posts/update/{postId}")
    suspend fun updatePost()

    @POST("/apis/posts/delete/{postId}")
    suspend fun deletePost(

    )
}