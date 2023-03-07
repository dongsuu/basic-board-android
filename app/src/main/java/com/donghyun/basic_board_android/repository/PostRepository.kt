package com.donghyun.basic_board_android.repository

import android.util.Log
import com.donghyun.basic_board_android.apis.PostApi
import com.donghyun.basic_board_android.dtos.CreatePostDto
import com.donghyun.basic_board_android.dtos.DetailsPostDto
import com.donghyun.basic_board_android.dtos.PostDto
import com.donghyun.basic_board_android.dtos.UpdatePostDto
import com.donghyun.basic_board_android.service.RetrofitService
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.create

class PostRepository {

    suspend fun getPostsByBoardName(boardName: String, token: String) : Response<List<PostDto>> {

        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val postApi = retrofit.create(PostApi::class.java)
        Log.d("TAG", "getPostsByBoardName: ${boardName}")
        Log.d("TAG", "getPostsByBoardName: ${token}")
        return postApi.getPostsByBoardName(
            boardName, token
        )
    }

    suspend fun createPostWithImages(post: CreatePostDto, images: List<MultipartBody.Part>, accessToken: String) : Response<String>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val postApi = retrofit.create(PostApi::class.java)

        return postApi.createPostWithImages(
            post, images, accessToken
        )
    }

    suspend fun createPost(post: CreatePostDto, accessToken: String) : Response<String>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val postApi = retrofit.create(PostApi::class.java)

        return postApi.createPost(
            post, accessToken
        )
    }

    suspend fun postDetails(postId: Long, accessToken: String) : Response<DetailsPostDto>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val postApi = retrofit.create(PostApi::class.java)

        return postApi.postDetails(
            postId, accessToken
        )
    }

    suspend fun updatePost(postId: Long, accessToken: String, updatePost: CreatePostDto) : Response<String>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val postApi = retrofit.create(PostApi::class.java)

        return postApi.updatePost(
            accessToken,
            postId,
            updatePost
        )
    }

    suspend fun deletePost(postId: Long, accessToken: String) : Response<String>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val postApi = retrofit.create(PostApi::class.java)

        return postApi.deletePost(
            accessToken,
            postId
        )
    }
}