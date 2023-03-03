package com.donghyun.basic_board_android.repository

import android.util.Log
import com.donghyun.basic_board_android.apis.MemberApi
import com.donghyun.basic_board_android.apis.PostApi
import com.donghyun.basic_board_android.dtos.PostDto
import com.donghyun.basic_board_android.service.RetrofitService
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.create
import java.io.File
import java.nio.file.Files

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

    suspend fun createPostWithImages(post: PostDto, images: List<MultipartBody.Part>, accessToken: String) : Response<String>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val postApi = retrofit.create(PostApi::class.java)

        return postApi.createPostWithImages(
            post, images, accessToken
        )
    }

    suspend fun createPost(post: PostDto, accessToken: String) : Response<String>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val postApi = retrofit.create(PostApi::class.java)

        return postApi.createPost(
            post, accessToken
        )
    }
}