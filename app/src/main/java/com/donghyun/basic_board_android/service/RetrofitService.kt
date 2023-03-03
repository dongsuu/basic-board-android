package com.donghyun.basic_board_android.service

import com.donghyun.basic_board_android.apis.HomeApi
import com.donghyun.basic_board_android.apis.MemberApi
import com.donghyun.basic_board_android.apis.PostApi
import com.donghyun.basic_board_android.constants.Constants.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitService {
    object RetrofitInstance {

        private var instance: Retrofit? = null
        private val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        fun getInstance(): Retrofit {
            if(instance == null){
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return instance!!
        }
    }
}