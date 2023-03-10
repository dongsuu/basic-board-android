package com.donghyun.basic_board_android.apis

import com.donghyun.basic_board_android.dtos.CreatePostDto
import com.donghyun.basic_board_android.dtos.DetailsPostDto
import com.donghyun.basic_board_android.dtos.PostDto
import com.donghyun.basic_board_android.dtos.UpdatePostDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface PostApi {

    @GET("/apis/posts/{boardName}")
    suspend fun getPostsByBoardName(
        @Path("boardName") boardName: String,
        @Header("Authorization") token: String
    ) : Response<List<PostDto>>

    @Multipart
    @POST("/apis/posts/upload/new")
    suspend fun createPostWithImages(
        @Part("data") post: CreatePostDto,
        @Part images: List<MultipartBody.Part>,
        @Header("Authorization") token: String
    ) : Response<String>

    @Multipart
    @POST("/apis/posts/new")
    suspend fun createPost(
        @Part("data") post: CreatePostDto,
        @Header("Authorization") token: String
    ) : Response<String>

    @GET("/apis/posts/details/{postId}")
    suspend fun postDetails(
        @Path("postId") postId: Long,
        @Header("Authorization") token: String
    ) : Response<DetailsPostDto>

    @POST("/apis/posts/update/{postId}")
    suspend fun updatePost(
        @Header("Authorization") token: String,
        @Path("postId") postId: Long,
        @Body updatePost: CreatePostDto
    ) : Response<String>

    @POST("/apis/posts/update/upload/{postId}")
    suspend fun updatePostWithImages(
        @Part("data") post: CreatePostDto,
        @Part images: List<MultipartBody.Part>,
        @Header("Authorization") token: String
    ) : Response<String>

    @POST("/apis/posts/delete/{postId}")
    suspend fun deletePost(
        @Header("Authorization") token: String,
        @Path("postId") postId: Long
    ) : Response<String>
}