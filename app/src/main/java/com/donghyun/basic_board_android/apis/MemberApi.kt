package com.donghyun.basic_board_android.apis

import com.donghyun.basic_board_android.dtos.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MemberApi {

    @POST("/apis/members/join")
    suspend fun join(
        @Body memberJoinDto: MemberJoinDto
    ) : Response<MemberJoinDto>

    @POST("/apis/members/login")
    suspend fun login(
        @Body memberLoginRequestDto: MemberLoginRequestDto
    ) : Response<TokenInfo>

    @POST("/apis/members/logout")
    suspend fun logout(
        @Header("Authorization") token: String
    ) : Response<String>

    @GET("/apis/members/myInfo")
    suspend fun myInfo(
        @Header("Authorization") token: String
    ) : Response<MyInfoDto>

    @POST("/apis/members/update/myInfo")
    suspend fun updateMyInfo(
        @Header("Authorization") token: String,
        @Body updateMemberDto: UpdateMemberDto
    ): Response<String>
}