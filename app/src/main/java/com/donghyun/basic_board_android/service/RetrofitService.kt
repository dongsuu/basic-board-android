package com.donghyun.basic_board_android.service

import com.donghyun.basic_board_android.apis.HomeApi
import com.donghyun.basic_board_android.apis.MemberApi
import com.donghyun.basic_board_android.constants.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitService {
    object RetrofitInstance {

        val memberService: MemberApi = initMemberService()
        val homeService: HomeApi = initHomeService()

        private fun initMemberService(): MemberApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MemberApi::class.java)

        private fun initHomeService(): HomeApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HomeApi::class.java)
    }
}