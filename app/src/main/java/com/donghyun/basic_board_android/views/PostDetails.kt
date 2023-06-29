package com.donghyun.basic_board_android.views

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.donghyun.basic_board_android.utility.FormDataUtil
import com.donghyun.basic_board_android.viewModel.CommentViewModel
import com.donghyun.basic_board_android.viewModel.HomeViewModel
import com.donghyun.basic_board_android.viewModel.MemberViewModel
import com.donghyun.basic_board_android.viewModel.PostViewModel
import java.time.format.DateTimeFormatter

@Composable
fun PostDetails(
    postViewModel: PostViewModel,
    homeViewModel: HomeViewModel,
    memberViewModel: MemberViewModel,
    commentViewModel: CommentViewModel,
    navController: NavController
){
    TopAppBar(navController = navController)
    val post = postViewModel.getPostDetails().value!!

    val scrollState = rememberScrollState()
    val selectedCommentId = remember {
        mutableStateOf(0L)
    }

    Column(
        modifier = Modifier
            .padding(60.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "제목",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = post.title,
            style = TextStyle(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

        Text(
            text = "작성자",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = post.author,
            style = TextStyle(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

        Text(
            text = "작성일",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = post.lastModifiedDate,
            style = TextStyle(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)


        Text(
            text = "내용",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = post.content,
            style = TextStyle(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

        Text(
            text = "게시판 카테고리",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = post.boardName,
            style = TextStyle(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

        Text(text = "이미지 파일")
        post.uploadFilePaths.forEach {
            Log.d("TAG", "UploadFilePath: ${it}")
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (homeViewModel.getCurrentMemberInfo().value!!.name == post.author) {
                Button(onClick = {
                    navController.navigate("updatePost")
                },
                    modifier = Modifier.padding(3.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text(text = "글 수정하기")
                }
                Button(onClick = {
                    postViewModel.deletePost(
                        memberViewModel.getRequestToken().value,
                        postViewModel.getCurrentPostId().value,
                        post.boardName,
                        navController
                    )},
                    modifier = Modifier.padding(3.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text(text = "글 삭제")
                }
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))
        Divider(color = Color.Gray, thickness = 1.dp)

//        Text(
//            text = "댓글",
//            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
//        )
//
//        val content = remember {
//            mutableStateOf("")
//        }
//        val isReply = remember {
//            mutableStateOf(false)
//        }
//
//        if(!isReply.value){
//            TextField(
//                label = { Text(text = "댓글을 입력하세요.") },
//                value = content.value,
//                onValueChange = {c -> content.value = c}
//            )
//
//            Button(onClick = {
//                commentViewModel.createComment(
//                    post.postId,
//                    memberViewModel.getRequestToken().value,
//                    content.value,
//                    navController
//                )
//            }) {
//                Text("댓글 등록")
//            }
//        } else {
//
//            TextField(
//                label = { Text(text = "대댓글을 입력하세요.") },
//                value = content.value,
//                onValueChange = {c -> content.value = c}
//            )
//
//            Button(onClick = {
//                commentViewModel.createReply(
//                    selectedCommentId.value,
//                    memberViewModel.getRequestToken().value,
//                    content.value,
//                    navController
//                )
//            }) {
//                Text("대댓글 등록")
//            }
//        }
//
//        for (parentComment in post.parentComments) {
//            Divider(color = Color.Gray, thickness = 1.dp)
//
//            Text(
//                text = parentComment.authorNickname,
//                style = TextStyle(fontSize = 14.sp)
//            )
//            Text(
//                text = parentComment.createDate,
//                style = TextStyle(fontSize = 14.sp)
//            )
//            if(parentComment.authorNickname == homeViewModel.getCurrentMemberInfo().value!!.nickname){
//                Button(
//                    modifier = Modifier.size(60.dp),
//                    onClick = {
//                    commentViewModel.deleteComment(
//                        parentComment.commentId,
//                        memberViewModel.getRequestToken().value,
//                        navController
//                    )
//                }) {
//                    Text("삭제")
//                }
//            }
//
//            Text(
//                text = parentComment.content,
//                style = TextStyle(fontSize = 14.sp)
//            )
//            Button(
//                modifier = Modifier.size(60.dp),
//                onClick = {
//                    isReply.value = !isReply.value
//                    if(isReply.value) selectedCommentId.value = parentComment.commentId
//                    else selectedCommentId.value = 0L
//
//                }) {
//                val value:String = if(isReply.value) "대댓글 작성" else "댓글 작성"
//                Text(value)
//            }
//
//            Divider(color = Color.Gray, thickness = 0.5.dp)
//            for (reply in parentComment.replies) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ){
//                    Icon(
//                        imageVector = Icons.Default.ArrowForward,
//                        contentDescription = ""
//                    )
//                    Text(
//                        text = reply.replyAuthorNickname,
//                        style = TextStyle(fontSize = 10.sp)
//                    )
//                    Text(
//                        text = reply.createDate,
//                        style = TextStyle(fontSize = 10.sp)
//                    )
//                }
//                Text(
//                    text = reply.content,
//                    style = TextStyle(fontSize = 10.sp)
//                )
//                if(reply.replyAuthorNickname == homeViewModel.getCurrentMemberInfo().value!!.nickname){
//                    Button(onClick = { commentViewModel.deleteReply(
//                        reply.commentId,
//                        memberViewModel.getRequestToken().value,
//                        navController
//                    ) }) {
//                        Text(text = "대댓글 삭제")
//                    }
//                }
//            }
//        }
        // Comment section header
        Text(
            text = "댓글",
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
        )

// Input field for comments
        val content = remember {
            mutableStateOf("")
        }
        val isReply = remember {
            mutableStateOf(false)
        }

        if (!isReply.value) {
            TextField(
                label = { Text(text = "댓글을 입력하세요.") },
                value = content.value,
                onValueChange = { c -> content.value = c }
            )

            Button(
                onClick = {
                    commentViewModel.createComment(
                        post.postId,
                        memberViewModel.getRequestToken().value,
                        content.value,
                        navController
                    )
                }
            ) {
                Text("등록")
            }
        } else {
            TextField(
                label = { Text(text = "대댓글을 입력하세요.") },
                value = content.value,
                onValueChange = { c -> content.value = c }
            )

            Button(
                onClick = {
                    commentViewModel.createReply(
                        selectedCommentId.value,
                        memberViewModel.getRequestToken().value,
                        content.value,
                        navController
                    )
                },
                modifier = Modifier.padding(3.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text("등록")
            }
        }

        for (parentComment in post.parentComments) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = parentComment.authorNickname,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Text(
                            text = parentComment.createDate,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                    if(parentComment.authorNickname == homeViewModel.getCurrentMemberInfo().value!!.nickname){
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            modifier = Modifier.width(50.dp).height(30.dp),
                            onClick = {
                                commentViewModel.deleteComment(
                                    parentComment.commentId,
                                    memberViewModel.getRequestToken().value,
                                    navController
                                )
                            }
                        ) {
                            Text(
                                text = "삭제",
                                fontSize = 10.sp
                            )
                        }
                    }
                }
                Text(
                    text = parentComment.content,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier.size(120.dp, 40.dp),
                        onClick = {
                            isReply.value = !isReply.value
                            if(isReply.value) selectedCommentId.value = parentComment.commentId
                            else selectedCommentId.value = 0L

                        }
                    ) {
                        val value:String = if(isReply.value) "대댓글 작성" else "댓글 작성"
                        Text(
                            text = value,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                for (reply in parentComment.replies) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = reply.replyAuthorNickname,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = reply.createDate,
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                            if(reply.replyAuthorNickname == homeViewModel.getCurrentMemberInfo().value!!.nickname){
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        commentViewModel.deleteReply(
                                            reply.commentId,
                                            memberViewModel.getRequestToken().value,
                                            navController
                                        )
                                    },
                                    modifier = Modifier.size(80.dp, 30.dp)
                                ) {
                                    Text(
                                        text = "삭제",
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = reply.content,
                            fontSize = 14.sp
                        )
                    }
                }
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}

