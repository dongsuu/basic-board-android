package com.donghyun.basic_board_android.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.donghyun.basic_board_android.dtos.CreateCommentDto
import com.donghyun.basic_board_android.repository.CommentRepository
import kotlinx.coroutines.launch

class CommentViewModel(
    private val commentRepository: CommentRepository
): ViewModel() {

    fun createComment(
        postId: Long,
        accessToken: String,
        content: String,
        navController: NavController
    ){
        val comment = CreateCommentDto(content)

        viewModelScope.launch {
            val response = commentRepository.createComment(postId, accessToken, comment)
            if(response.isSuccessful){
                Log.d("TAG", "createComment: ${response.body()}")
                navController.navigate("postDetails")
            } else {
                Log.d("TAG", "createCommentApi Failed")
                Log.d("TAG", "${response.errorBody()}")
                Log.d("TAG", "${response.raw()}")
                Log.d("TAG", "${response.code()}")
            }

        }
    }

    fun deleteComment(
        commentId: Long,
        accessToken: String,
        navController: NavController
    ){
        viewModelScope.launch {
            val response = commentRepository.deleteComment(commentId, accessToken)
            if(response.isSuccessful){
                navController.navigate("postDetails")
            } else {
                Log.d("TAG", "deleteCommentApi Failed")
                Log.d("TAG", "${response.errorBody()}")
                Log.d("TAG", "${response.raw()}")
                Log.d("TAG", "${response.code()}")
            }
        }
    }

    fun createReply(
        commentId: Long,
        accessToken: String,
        content: String,
        navController: NavController
    ){
        val reply =  CreateCommentDto(content)
        viewModelScope.launch {
            val response = commentRepository.createReply(commentId, accessToken, reply)
            if(response.isSuccessful){
                navController.navigate("postDetails")
            } else {
                Log.d("TAG", "createReply Failed")
                Log.d("TAG", "${response.errorBody()}")
                Log.d("TAG", "${response.raw()}")
                Log.d("TAG", "${response.code()}")
            }
        }
    }

    fun deleteReply(
        commentId: Long,
        accessToken: String,
        navController: NavController
    ){
        viewModelScope.launch {
            val response = commentRepository.deleteReply(commentId, accessToken)
            if(response.isSuccessful){
                navController.navigate("postDetails")
            } else {
                Log.d("TAG", "createReply Failed")
                Log.d("TAG", "${response.errorBody()}")
                Log.d("TAG", "${response.raw()}")
                Log.d("TAG", "${response.code()}")
            }
        }
    }
}