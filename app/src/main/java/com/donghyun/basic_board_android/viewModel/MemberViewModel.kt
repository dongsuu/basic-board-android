package com.donghyun.basic_board_android.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.donghyun.basic_board_android.dtos.*
import com.donghyun.basic_board_android.repository.MemberRepository
import kotlinx.coroutines.launch

class MemberViewModel(
    private val memberRepository: MemberRepository,
    private val homeViewModel: HomeViewModel
    ) : ViewModel() {

    private var joinMember: MutableState<MemberJoinDto?> = mutableStateOf(null)
    fun getMemberJoinResponse(): MutableState<MemberJoinDto?> {
        return joinMember
    }

    private var tokenInfo: MutableState<TokenInfo?> = mutableStateOf(null)
    private var requestToken: MutableState<String> = mutableStateOf("")

    fun getRequestToken(): MutableState<String>{
        return requestToken
    }

    fun getTokenInfo(): MutableState<TokenInfo?>{
        return tokenInfo
    }

    private var myInfo: MutableState<MyInfoDto?> = mutableStateOf(null)
    fun getMyInfo(): MutableState<MyInfoDto?>{
        return myInfo
    }

    fun joinMember(
        name: String,
        email: String,
        password: String,
        nickname: String,
        age: Int
    ){
        viewModelScope.launch {
            val memberJoinDto = MemberJoinDto(name, age, email, password, nickname)

            val response = memberRepository.join(
                memberJoinDto
            )

            if (response.isSuccessful) {
                Log.d("MemberViewModel", "joinMember: ${response.body()}")
                joinMember.value = response.body()!!
            } else {
                Log.d(TAG, "failed join")
                Log.e(TAG, "joinMember: ${response.errorBody()}")
            }
        }
    }

    fun login(
        email: String,
        password: String,
        navController: NavController
    ){
        viewModelScope.launch {
            val memberLoginDto = MemberLoginRequestDto(email, password)

            val response = memberRepository.login(memberLoginDto)

            if(response.isSuccessful){
                tokenInfo.value = response.body()
                Log.d(TAG, "login: ${tokenInfo.value}")
                requestToken.value = tokenInfo.value!!.grantType + " " + tokenInfo.value!!.accessToken
                navController.navigate("home")
                homeViewModel.getHome(requestToken.value, navController)

            } else {
                Log.d(TAG, "failed login")
                Log.e(TAG, "error: ${response.errorBody()}")
            }
        }
    }

    fun logout(
        navController: NavController
    ){
        viewModelScope.launch {
            val response = memberRepository.logout(getRequestToken().value)

            if(response.isSuccessful){
                navController.navigate("login")
            } else {
                Log.d(TAG, "failed logout")
                Log.e(TAG, "error: ${response.errorBody()}")
            }
        }
    }

    fun myInfo(
        accessToken: String,
        navController: NavController
    ){
        viewModelScope.launch{
            val response = memberRepository.myInfo(accessToken)

            if(response.isSuccessful){
                navController.navigate("myInfo"){
                    launchSingleTop = true
                    restoreState = true
                }
                myInfo.value = response.body()
            } else {
                Log.d(TAG, "myInfo: failed myInfo")
                Log.e(TAG, "error: ${response.errorBody()}")
            }
        }
    }

    fun updateMyInfo(
        navController: NavController,
        email: String,
        name: String,
        age: Int,
        nickname: String
    ){
        viewModelScope.launch {
            val updateMemberDto = UpdateMemberDto(email, name, age, nickname)
            val response = memberRepository.updateMyInfo(getRequestToken().value, updateMemberDto)
            if(response.isSuccessful){
                navController.navigate("myInfo")
            } else {
                Log.d(TAG, "myInfo: failed myInfo")
                Log.e(TAG, "error: ${response.errorBody()}")
            }
        }
    }


}