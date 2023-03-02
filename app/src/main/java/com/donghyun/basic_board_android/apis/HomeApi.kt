package com.donghyun.basic_board_android.apis

import com.donghyun.basic_board_android.dtos.HomeDto
import com.donghyun.basic_board_android.dtos.TokenInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface HomeApi {

    @GET("/apis/")
    suspend fun home(@Header("Authorization") token: String) : Response<HomeDto>
}