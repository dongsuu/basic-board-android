package com.donghyun.basic_board_android.service

import com.donghyun.basic_board_android.apis.MemberApi
import com.donghyun.basic_board_android.constants.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    object RetrofitInstance {

        val memberService: MemberApi = initService()

        private fun initService(): MemberApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MemberApi::class.java)
    }
}