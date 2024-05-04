package com.nhathuy.mviexample.data.api

import com.nhathuy.mviexample.data.model.User

class ApiHelperImpl(private val apiServer: ApiServer) :ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiServer.getUsers()
    }
}