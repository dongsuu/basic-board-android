package com.donghyun.basic_board_android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.donghyun.basic_board_android.dtos.MemberJoinDto
import com.donghyun.basic_board_android.repository.MemberRepository
import retrofit2.Response

class MainViewModel(private val memberRepository: MemberRepository) : ViewModel() {
    val memberJoinResponse : MutableLiveData<Response<MemberJoinDto>> = MutableLiveData()

    fun
            join(){
//        viewModelScope.launch {
//            val response = memberRepository.join()
//            memberJoinResponse.value = response
//        }
    }
}