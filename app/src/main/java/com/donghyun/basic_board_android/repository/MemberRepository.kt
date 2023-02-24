package com.donghyun.basic_board_android.repository

import android.util.Log
import com.donghyun.basic_board_android.dtos.MemberJoinDto
import com.donghyun.basic_board_android.dtos.MemberLoginRequestDto
import com.donghyun.basic_board_android.dtos.TokenInfo
import com.donghyun.basic_board_android.service.RetrofitService
import retrofit2.Response

class MemberRepository {

    suspend fun join(
        memberJoinDto: MemberJoinDto
    ) : Response<MemberJoinDto>{
        Log.d("TAG", "join: ${memberJoinDto}")
        return RetrofitService.RetrofitInstance.memberService.join(
            memberJoinDto
        )
    }

    suspend fun login(
        memberLoginRequestDto: MemberLoginRequestDto
    ) : Response<TokenInfo>{
        return RetrofitService.RetrofitInstance.memberService.login(
            memberLoginRequestDto
        )
    }
}