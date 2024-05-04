package com.nhathuy.mviexample.data.api

import com.nhathuy.mviexample.data.model.User

interface ApiHelper {
    suspend fun getUsers():List<User>
}