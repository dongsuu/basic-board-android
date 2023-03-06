package com.donghyun.basic_board_android.viewModel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.donghyun.basic_board_android.dtos.CreatePostDto
import com.donghyun.basic_board_android.dtos.DetailsPostDto
import com.donghyun.basic_board_android.dtos.PostDto
import com.donghyun.basic_board_android.dtos.UpdatePostDto
import com.donghyun.basic_board_android.repository.PostRepository
import com.donghyun.basic_board_android.utility.FormDataUtil
import com.donghyun.basic_board_android.utility.UriUtil
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class PostViewModel(
    private val postRepository: PostRepository
) :ViewModel() {

    private var posts : MutableState<List<PostDto>?> = mutableStateOf(null)
    fun getPosts(): MutableState<List<PostDto>?> {
        return posts;
    }

    private var imageUri: MutableState<Uri?> = mutableStateOf(null)
    fun getImageUri(): MutableState<Uri?>{
        return imageUri
    }

    private var postDetails : MutableState<DetailsPostDto?> = mutableStateOf(null)
    fun getPostDetails(): MutableState<DetailsPostDto?>{
        return postDetails
    }

    private var currentPostId: MutableState<Long> = mutableStateOf(0L)
    fun getCurrentPostId() : MutableState<Long>{
        return currentPostId
    }

    fun createPost(
        title: String,
        content: String,
        boardName: String,
        uris : List<Uri?>,
        context: Context,
        navController: NavController,
        accessToken: String
    ){

        val multipartList = mutableListOf<MultipartBody.Part>()
        Log.d("TAG", "createPost: ${uris.size}")
        if(uris.isNotEmpty()){
            for (uri in uris) {
                Log.d("TAG", "createPost: ${uri}")
                val file = UriUtil.toFile(context, uri!!)
                val multipart = FormDataUtil.getImageMultipart("files", file)
                multipartList.add(multipart)
            }
        }

        val post = CreatePostDto(title, content, boardName)

        viewModelScope.launch {
            if(uris.isNotEmpty()){
                val response = postRepository.createPostWithImages(post, multipartList, accessToken)
                if(response.isSuccessful){
                    Log.d("TAG", "createPostApi Success: ${response.body()}")
                    navController.navigate("postHome/${boardName}")
                    imageUri.value = null
                } else {
                    Log.d("TAG", "failed createPost Api")
                    Log.d("TAG", "createPost: ${response.message()}")
                    Log.d("TAG", "createPost: ${response.raw()}")
                }
            } else {
                val response = postRepository.createPost(post, accessToken)
                if(response.isSuccessful){
                    navController.navigate("postHome/${boardName}")
                    imageUri.value = null
                } else {
                    Log.d("TAG", "failed createPost Api")
                    Log.d("TAG", "createPost: ${response.message()}")
                    Log.d("TAG", "createPost: ${response.raw()}")
                }
            }
        }
    }

    fun getPostsByBoardName(
        requestToken: String,
        boardName: String
    ){
        viewModelScope.launch {
            val response = postRepository.getPostsByBoardName(boardName, requestToken)
            if(response.isSuccessful){
                Log.d("TAG", "getPostsByBoardName: ${response.body()}")
                posts.value = response.body()
            } else {
                Log.d("TAG", "Failed getPostByBoardName Api")
                Log.d("TAG", "getPostsByBoardName: ${response.errorBody()}")
                Log.d("TAG", "getPostsByBoardName: ${response.code()}")
                Log.d("TAG", "getPostsByBoardName: ${response.message()}")
                Log.d("TAG", "getPostsByBoardName: ${response.raw()}")

            }

        }
    }

    fun postDetails(
        accessToken: String,
        postId: Long,
        navController: NavController
    ) {
        viewModelScope.launch {
            val response = postRepository.postDetails(postId, accessToken)
            if(response.isSuccessful){
                Log.d("TAG", "postDetailsApi Success: ${response.body()}")
                postDetails.value = response.body()
                currentPostId.value = postId
                navController.navigate("postDetails")
            } else {
                Log.d("TAG", "postDetailsApi Failed")
                Log.d("TAG", "${response.errorBody()}")
                Log.d("TAG", "${response.raw()}")
                Log.d("TAG", "${response.code()}")
            }
        }
    }

    fun updatePost(
        accessToken: String,
        postId: Long,
        navController: NavController,
        title: String,
        content: String,
        boardName: String,
        uris : List<Uri?>,
        context: Context
    ){
        val multipartList = mutableListOf<MultipartBody.Part>()
        Log.d("TAG", "updatePost: ${uris.size}")
        if(uris.isNotEmpty()){
            for (uri in uris) {
                Log.d("TAG", "updatePost: ${uri}")
                val file = UriUtil.toFile(context, uri!!)
                val multipart = FormDataUtil.getImageMultipart("files", file)
                multipartList.add(multipart)
            }
        }

        val updatePost = CreatePostDto(title, content, boardName)

        viewModelScope.launch {
            if(uris.isNotEmpty()){
                val response = postRepository.createPostWithImages(updatePost, multipartList, accessToken)
                if(response.isSuccessful){
                    Log.d("TAG", "createPostApi Success: ${response.body()}")
                    navController.navigate("postHome/${boardName}")
                    imageUri.value = null
                } else {
                    Log.d("TAG", "failed createPost Api")
                    Log.d("TAG", "createPost: ${response.message()}")
                    Log.d("TAG", "createPost: ${response.raw()}")
                }
            } else {
                val response = postRepository.updatePost(postId, accessToken, updatePost)
                if(response.isSuccessful){
                    Log.d("TAG", "updatePost: ${response.body()}")
                    navController.navigate("postHome/${boardName}")
                    imageUri.value = null
                } else {
                    Log.d("TAG", "failed updatePost Api")
                    Log.d("TAG", "updatePost: ${response.message()}")
                    Log.d("TAG", "updatePost: ${response.raw()}")
                }
            }
        }
    }

    fun deletePost(
        accessToken: String,
        postId: Long,
        boardName: String,
        navController: NavController
    ){
        viewModelScope.launch {
            val response = postRepository.deletePost(postId, accessToken)
            if(response.isSuccessful){
                navController.navigate("postHome/${boardName}")
            } else {
                Log.d("TAG", "failed deletePost Api")
                Log.d("TAG", "updatePost: ${response.message()}")
                Log.d("TAG", "updatePost: ${response.raw()}")
            }
        }
    }

}