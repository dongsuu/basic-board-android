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
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.donghyun.basic_board_android.viewModel.MemberViewModel
import com.donghyun.basic_board_android.viewModel.PostViewModel

@Composable
fun PostForm(
    navController: NavController,
    postViewModel: PostViewModel,
    imageUri: MutableState<Uri?>,
    memberViewModel: MemberViewModel,
    boardName: String?
){
    TopAppBar(navController = navController)

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


    var title = remember {
        mutableStateOf("")
    }

    var content = remember {
        mutableStateOf("")
    }

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
                },
                modifier = Modifier.padding(3.dp)
            ) {
                Text(text = "이미지 선택 취소")
            }
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween){
            Button(onClick = {
                postViewModel.createPost(
                    title.value,
                    content.value,
                    boardName!!,
                    imageUris,
                    context,
                    navController,
                    memberViewModel.getRequestToken().value)
                 }, modifier = Modifier.padding(3.dp)
            ) {
                Text(text = "글쓰기 완료")
            }

            Button(
                onClick = {
                    navController.navigate("postHome/${boardName}")
                    imageUri.value = null
                    postViewModel.getImageUri().value = null
                }, modifier = Modifier.padding(3.dp)
            ) {
                Text(text = "글쓰기 취소")
            }
        }
    }

}