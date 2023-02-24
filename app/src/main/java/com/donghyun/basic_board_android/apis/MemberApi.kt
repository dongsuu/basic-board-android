package com.donghyun.basic_board_android.apis

import com.donghyun.basic_board_android.dtos.MemberJoinDto
import com.donghyun.basic_board_android.dtos.MemberLoginRequestDto
import com.donghyun.basic_board_android.dtos.TokenInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberApi {

    @POST("/members/join")
    suspend fun join(
        @Body memberJoinDto: MemberJoinDto
    ) : Response<MemberJoinDto>

    @POST("/members/login")
    suspend fun login(
        @Body memberLoginRequestDto: MemberLoginRequestDto
    ) : Response<TokenInfo>
}