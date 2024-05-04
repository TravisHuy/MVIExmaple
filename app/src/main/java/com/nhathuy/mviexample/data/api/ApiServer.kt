package com.nhathuy.mviexample.data.api

import com.nhathuy.mviexample.data.model.User
import retrofit2.http.GET
//phương thức http để giao tiếp với api
interface ApiServer {
    @GET("users")
    suspend fun getUsers():List<User>
}