package com.donghyun.basic_board_android.viewModel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.donghyun.basic_board_android.dtos.PostDto
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

    fun createPost(
        title: String,
        content: String,
        boardName: String,
        uris: List<Uri?>,
        context: Context,
        navController: NavController,
        accessToken: String
    ){

        val multipartList = mutableListOf<MultipartBody.Part>()

        if(uris.isNotEmpty()){
            for (uri in uris) {
                val file = UriUtil.toFile(context, uri!!)
                val multipart = FormDataUtil.getImageMultipart("files", file)
                multipartList.add(multipart)
            }
        }

        val post = PostDto(title, content, boardName)

        viewModelScope.launch {
            if(uris.isNotEmpty()){
                val response = postRepository.createPostWithImages(post, multipartList, accessToken)
                if(response.isSuccessful){
                    navController.navigate("postHome/${boardName}")
                } else {
                    Log.d("TAG", "failed createPost Api")
                    Log.d("TAG", "createPost: ${response.message()}")
                    Log.d("TAG", "createPost: ${response.raw()}")
                }
            } else {
                val response = postRepository.createPost(post, accessToken)
                if(response.isSuccessful){
                    navController.navigate("postHome/${boardName}")
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
        Log.d("TAG", "requestToken: ${requestToken}")
        Log.d("TAG", "boardname: ${boardName}")
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
}