package com.donghyun.basic_board_android.views

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.donghyun.basic_board_android.utility.UriUtil
import com.donghyun.basic_board_android.viewModel.HomeViewModel
import com.donghyun.basic_board_android.viewModel.MemberViewModel
import com.donghyun.basic_board_android.viewModel.PostViewModel
import java.io.File

@Composable
fun UpdatePost(
    navController: NavController,
    imageUri: MutableState<Uri?>,
    postViewModel: PostViewModel,
    memberViewModel: MemberViewModel
){
    TopAppBar(navController = navController)

    val previousPost = postViewModel.getPostDetails().value!!
    val previousImages = remember {
        mutableStateListOf(previousPost.uploadFilePaths)
    }
    val imageUris = remember {
        mutableStateListOf<Uri?>()
    }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageUri.value = it
            imageUris.add(it)
        }
    )


    val title = remember {
        mutableStateOf(previousPost.title)
    }

    val content = remember {
        mutableStateOf(previousPost.content)
    }

    val boardName = remember {
        mutableStateOf(previousPost.boardName)
    }

    TopAppBar(navController = navController)
    Column(
        modifier = Modifier.padding(60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            label = { Text(text = "제목") },
            value = title.value,
            onValueChange = {t -> title.value = t}
        )

        Spacer(modifier = Modifier.padding(10.dp))

        TextField(
            label = { Text(text = "내용") },
            value = content.value,
            onValueChange = {c -> content.value = c}
        )

        Spacer(modifier = Modifier.padding(10.dp))

        var isDropDownMenuExpanded by remember {
            mutableStateOf(false)
        }

        Button(onClick = { isDropDownMenuExpanded = true }) {
            Text(text = boardName.value)
        }

        DropdownMenu(
            modifier = Modifier.wrapContentSize(),
            expanded = isDropDownMenuExpanded,
            onDismissRequest = { isDropDownMenuExpanded = false }) {
            DropdownMenuItem(onClick = {
                boardName.value = "FREE"
            }) {
                Text("자유 게시판")
            }
            DropdownMenuItem(onClick = {
                boardName.value = "STUDY"
            }) {
                Text("스터디 게시판")
            }
            DropdownMenuItem(onClick = {
                boardName.value = "SPORTS"
            }) {
                Text("스포츠 게시판")
            }
        }
        
        if(previousPost.uploadFilePaths.isNotEmpty() && imageUri.value == null){
            for (im in previousPost.uploadFilePaths) {
                AsyncImage(
                    model = im,
                    contentDescription = "previous image",
                    contentScale = ContentScale.Fit)
            }
        }

        val bitmap = imageUri.value?.let{
            if(Build.VERSION.SDK_INT < 28){
                MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(bitmap != null){
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "image"
                )
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween){
            Button(
                onClick = {
                    launcher.launch("image/*")
                },
                modifier = Modifier.padding(3.dp)
            ) {
                Text(text = "이미지 선택")
            }
            Button(onClick = {
                imageUri.value = null
                postViewModel.getImageUri().value = null
                previousPost.uploadFilePaths.clear()
                previousImages.clear()
            },
                modifier = Modifier.padding(3.dp)
            ) {
                Text(text = "이미지 선택 취소")
            }
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween){
            Button(onClick = {
                postViewModel.updatePost(
                    memberViewModel.getRequestToken().value,
                    postViewModel.getCurrentPostId().value,
                    navController,
                    title.value,
                    content.value,
                    boardName.value,
                    imageUris,
                    context
                )
            }, modifier = Modifier.padding(3.dp)
            ) {
                Text(text = "수정 완료")
            }

            Button(
                onClick = {
                    navController.navigate("postHome/${boardName}")
                    imageUri.value = null
                    postViewModel.getImageUri().value = null
                }, modifier = Modifier.padding(3.dp)
            ) {
                Text(text = "수정 취소")
            }
        }
    }
}

@Composable
fun DropDownBoardCategory(boardName: String){

}