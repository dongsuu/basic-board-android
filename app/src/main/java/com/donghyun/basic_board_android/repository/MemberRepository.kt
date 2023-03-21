package com.donghyun.basic_board_android.repository

import android.util.Log
import com.donghyun.basic_board_android.apis.HomeApi
import com.donghyun.basic_board_android.apis.MemberApi
import com.donghyun.basic_board_android.apis.PostApi
import com.donghyun.basic_board_android.dtos.HomeDto
import com.donghyun.basic_board_android.dtos.MemberJoinDto
import com.donghyun.basic_board_android.dtos.MemberLoginRequestDto
import com.donghyun.basic_board_android.dtos.TokenInfo
import com.donghyun.basic_board_android.service.RetrofitService
import retrofit2.Response
import retrofit2.create

class MemberRepository {

    suspend fun join(
        memberJoinDto: MemberJoinDto
    ) : Response<MemberJoinDto>{
        Log.d("TAG", "join: ${memberJoinDto}")
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val memberApi = retrofit.create(MemberApi::class.java)
        return memberApi.join(
            memberJoinDto
        )
    }

    suspend fun login(
        memberLoginRequestDto: MemberLoginRequestDto
    ) : Response<TokenInfo>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val memberApi = retrofit.create(MemberApi::class.java)
        return memberApi.login(
            memberLoginRequestDto
        )
    }

    suspend fun getCurrentMemberInfo(
        tokenInfo: String
    ) : Response<HomeDto>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val homeApi = retrofit.create(HomeApi::class.java)
        return homeApi.home(tokenInfo)
    }

    suspend fun logout(
        accessToken: String
    ) : Response<String>{
        val retrofit = RetrofitService.RetrofitInstance.getInstance()
        val memberApi = retrofit.create(MemberApi::class.java)
        return memberApi.logout(accessToken)
    }
}