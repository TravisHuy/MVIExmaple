package com.nhathuy.mviexample.data.repository

import com.nhathuy.mviexample.data.api.ApiHelper
// repository để request dữ liệu, chúng ta gọi phương thức getUsers từ viewmodel
class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers()= apiHelper.getUsers()
}