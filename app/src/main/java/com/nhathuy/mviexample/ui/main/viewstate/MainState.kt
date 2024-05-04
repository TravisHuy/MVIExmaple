package com.nhathuy.mviexample.ui.main.viewstate

import com.nhathuy.mviexample.data.model.User
//xác định trạng thái chờ đang tải người dùng lỗi. mỗi trạng thái có thể được tải vào chế độ xem theo ý định của người dùng
sealed class MainState{
    object Idle: MainState()
    object Loading: MainState()
    data class Users(val user:List<User>): MainState()
    data class Error(val error:String?):MainState()
}
