package com.donghyun.basic_board_android.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.donghyun.basic_board_android.dtos.HomeDto
import com.donghyun.basic_board_android.dtos.TokenInfo
import com.donghyun.basic_board_android.repository.MemberRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val memberRepository: MemberRepository,
): ViewModel() {
    private val currentMemberInfo: MutableState<HomeDto?> = mutableStateOf(null)

    fun getCurrentMemberInfo(): MutableState<HomeDto?>{
        return currentMemberInfo
    }

    fun getHome(tokenInfo: String, navController: NavController) {
        viewModelScope.launch {
            val response = memberRepository.getCurrentMemberInfo(tokenInfo = tokenInfo)
            if(response.isSuccessful){
                val homeDto = response.body()
                currentMemberInfo.value = homeDto
                navController.navigate("home")
            } else {
                Log.d("TAG", "getHomeApi Failed")
                Log.e("TAG", "getHome: ${response.errorBody()}")
            }
        }
    }
}